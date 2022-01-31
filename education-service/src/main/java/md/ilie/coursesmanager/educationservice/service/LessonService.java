package md.ilie.coursesmanager.educationservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Comment;
import md.ilie.coursesmanager.educationservice.entity.Lesson;
import md.ilie.coursesmanager.educationservice.entity.dto.mapper.EducationServiceMapper;
import md.ilie.coursesmanager.educationservice.entity.dto.request.CommentRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.request.LessonRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.LessonResponseDto;
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

  public List<LessonResponseDto> findAll() {

    return new ArrayList<>(mapper.toLessonResponseDtoList(repository.findAll()));
  }

  public LessonResponseDto findById(int id) {
    return mapper.toLessonResponseDto(repository.findById(id).orElseThrow(
        () -> new NoSuchElementException("Could not find lesson: [" + id + "]")));
  }

  public LessonResponseDto save(LessonRequestDto lessonRequestDto) {

    Lesson lessonEntity = mapper.toLessonEntity(lessonRequestDto);
    lessonEntity.setId(sequenceGeneratorService.generateSequence(Comment.SEQUENCE_NAME));
    return mapper.toLessonResponseDto(repository.save(lessonEntity));
  }

  public void delete(int id) {

    repository.deleteById(id);
  }

  public LessonResponseDto update(int id, LessonRequestDto lessonRequestDto) {

    if (repository.existsById(id)) {
      return mapper.toLessonResponseDto(repository.save(mapper.toLessonEntity(lessonRequestDto)));
    }
    throw new NoSuchElementException("Could not find lesson: [" + id + "]");
  }

  public LessonResponseDto addStudentsToLesson(Integer lessonId, List<StudentEntity> students) {
    Lesson lesson = repository.findById(lessonId).orElseThrow(
        () -> new NoSuchElementException("Could not find lesson: [" + lessonId + "]"));
    lesson.addStudents(students);

    return mapper.toLessonResponseDto(repository.save(lesson));
  }

  public LessonResponseDto addCommentToLesson(Integer lessonId, CommentRequestDto commentRequestDto) {

    Comment comment = mapper.toCommentEntity(commentRequestDto);
    comment.setId(sequenceGeneratorService.generateSequence(Comment.SEQUENCE_NAME));
    Lesson lesson = repository.findById(lessonId).orElseThrow(
        () -> new NoSuchElementException("Could not find lesson: [" + lessonId + "]"));
    lesson.addComments(List.of(comment));

    return mapper.toLessonResponseDto(repository.save(lesson));
  }

  public List<LessonResponseDto> getLessonsByUserId(Integer id) {

    List<Lesson> lessons = repository.getUserLessons(id);

    return mapper.toLessonResponseDtoList(lessons);
  }
}
