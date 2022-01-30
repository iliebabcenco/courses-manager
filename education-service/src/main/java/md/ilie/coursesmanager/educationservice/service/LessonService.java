package md.ilie.coursesmanager.educationservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Comment;
import md.ilie.coursesmanager.educationservice.entity.Lesson;
import md.ilie.coursesmanager.educationservice.entity.dto.LessonDto;
import md.ilie.coursesmanager.educationservice.entity.dto.mapper.EducationServiceMapper;
import md.ilie.coursesmanager.educationservice.repository.LessonRepository;
import md.ilie.coursesmanager.educationservice.util.mongo.SequenceGeneratorService;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LessonService {

  private final LessonRepository repository;
  private final EducationServiceMapper mapper;
  private final SequenceGeneratorService sequenceGeneratorService;

  public List<Lesson> findAllLessonsByIds(Integer... ids) {
    List<Lesson> lessonsList = new ArrayList<>();
    repository.findAllById(Arrays.asList(ids)).forEach(lessonsList::add);
    return lessonsList;
  }

  public List<Lesson> findAll() {
    return new ArrayList<>(repository.findAll());
  }

  public Lesson findById(int id) {
    return repository.findById(id).orElseThrow(
        () -> new NoSuchElementException("Could not find lesson: [" + id + "]"));
  }

  public Lesson save(Lesson lessonEntity) {

    lessonEntity.setId(sequenceGeneratorService.generateSequence(Comment.SEQUENCE_NAME));
    return repository.save(lessonEntity);
  }

  public void delete(int id) {

    repository.deleteById(id);
  }

  public Lesson update(int id, Lesson lessonEntity) {

    if (repository.existsById(id)) {
      repository.save(lessonEntity);
    }
    throw new NoSuchElementException("Could not find lesson: [" + id + "]");
  }

  public LessonDto addStudentsToLesson(Integer lessonId, List<StudentEntity> students) {
    Lesson lesson = repository.findById(lessonId).orElseThrow(
        () -> new NoSuchElementException("Could not find lesson: [" + lessonId + "]"));
    lesson.addStudents(students);

    return mapper.toLessonDto(repository.save(lesson));
  }

  public LessonDto addCommentToLesson(Integer lessonId, Comment comment) {

    comment.setId(sequenceGeneratorService.generateSequence(Comment.SEQUENCE_NAME));
    Lesson lesson = repository.findById(lessonId).orElseThrow(
        () -> new NoSuchElementException("Could not find lesson: [" + lessonId + "]"));
    lesson.addComments(List.of(comment));

    return mapper.toLessonDto(repository.save(lesson));
  }
}
