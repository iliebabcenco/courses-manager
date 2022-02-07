package md.ilie.coursesmanager.educationservice.service;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Comment;
import md.ilie.coursesmanager.educationservice.entity.Lesson;
import md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse.CommentGatewayResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse.LessonGatewayResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.mapper.EducationServiceMapper;
import md.ilie.coursesmanager.educationservice.entity.dto.response.LessonResponseDto;
import md.ilie.coursesmanager.educationservice.repository.LessonRepository;
import md.ilie.coursesmanager.educationservice.util.mongo.SequenceGeneratorService;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

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

  public LessonResponseDto save(LessonGatewayResponseDto lessonGatewayResponseDto) {

    Lesson lessonEntity = mapper.toLessonEntity(lessonGatewayResponseDto.getLessonRequestDto());
    lessonEntity.addStudents(lessonGatewayResponseDto.getStudentEntities());
    lessonEntity.setId(sequenceGeneratorService.generateSequence(Comment.SEQUENCE_NAME));
    return mapper.toLessonResponseDto(repository.save(lessonEntity));
  }

  public void delete(int id) {

    repository.deleteById(id);
  }

  public LessonResponseDto update(int id, LessonGatewayResponseDto lessonGatewayResponseDto) {

    if (repository.existsById(id)) {
      Lesson lessonEntity = mapper.toLessonEntity(lessonGatewayResponseDto.getLessonRequestDto());
      lessonEntity.addStudents(lessonGatewayResponseDto.getStudentEntities());
      return mapper.toLessonResponseDto(repository.save(lessonEntity));
    }
    throw new NoSuchElementException("Could not find lesson: [" + id + "]");
  }

  public LessonResponseDto addStudentsToLesson(Integer lessonId, List<StudentEntity> students) {
    Lesson lesson = repository.findById(lessonId).orElseThrow(
        () -> new NoSuchElementException("Could not find lesson: [" + lessonId + "]"));
    lesson.addStudents(students);

    return mapper.toLessonResponseDto(repository.save(lesson));
  }

  public LessonResponseDto addCommentToLesson(Integer lessonId, CommentGatewayResponseDto commentGatewayResponseDto) {

    Comment comment = mapper.toCommentEntity(commentGatewayResponseDto.getCommentRequestDto());
    comment.setId(sequenceGeneratorService.generateSequence(Comment.SEQUENCE_NAME));
    comment.setUsername(commentGatewayResponseDto.getUsername());
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
