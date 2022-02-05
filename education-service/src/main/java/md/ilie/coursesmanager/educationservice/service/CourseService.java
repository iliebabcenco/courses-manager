package md.ilie.coursesmanager.educationservice.service;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Comment;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.Lesson;
import md.ilie.coursesmanager.educationservice.entity.Mark;
import md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse.CommentGatewayResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse.CourseGatewayResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse.LessonGatewayResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse.MarkGatewayResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.mapper.EducationServiceMapper;
import md.ilie.coursesmanager.educationservice.entity.dto.response.CourseResponseDto;
import md.ilie.coursesmanager.educationservice.repository.CourseRepository;
import md.ilie.coursesmanager.educationservice.repository.LessonRepository;
import md.ilie.coursesmanager.educationservice.util.mongo.SequenceGeneratorService;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import md.ilie.coursesmanager.userservice.entity.TeacherEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CourseService {

  private final CourseRepository repository;
  private final LessonRepository lessonRepository;
  private final EducationServiceMapper mapper;
  private final SequenceGeneratorService sequenceGeneratorService;

  public List<CourseResponseDto> findAll() {

    List<Course> courseEntities = repository.findAll();

    return mapper.toCourseResponseDtoList(courseEntities);
  }

  public CourseResponseDto findById(int id) {

    Course course = repository.findById(id).orElseThrow(
        () -> new NoSuchElementException("Could not find course: [" + id + "]"));

    return mapper.toCourseResponseDto(course);
  }

  public CourseResponseDto save(CourseGatewayResponseDto courseGatewayResponseDto) {

    Course courseEntity = mapper.toCourseEntity(courseGatewayResponseDto.getRequestDto());
    courseEntity.setTeacher(courseGatewayResponseDto.getTeacherEntity());
    courseEntity.addStudents(courseGatewayResponseDto.getStudentEntityList());
    courseEntity.setId(sequenceGeneratorService.generateSequence(Comment.SEQUENCE_NAME));
    Course course = repository.save(courseEntity);

    return mapper.toCourseResponseDto(course);
  }

  public void delete(int id) {

    repository.deleteById(id);
  }

  public CourseResponseDto update(int id, CourseGatewayResponseDto courseGatewayResponseDto) {

    Course course;
    if (repository.existsById(id)) {
      Course courseEntity = mapper.toCourseEntity(courseGatewayResponseDto.getRequestDto());
      courseEntity.setTeacher(courseGatewayResponseDto.getTeacherEntity());
      courseEntity.addStudents(courseGatewayResponseDto.getStudentEntityList());
      courseEntity.setId(id);
      course = repository.save(courseEntity);

      return mapper.toCourseResponseDto(course);
    }

    throw new NoSuchElementException("Could not find course: [" + id + "]");
  }

  public List<CourseResponseDto> getCoursesByUserId(Integer id) {

    List<Course> courseEntities = repository.getUserCourses(id);

    return mapper.toCourseResponseDtoList(courseEntities);
  }

  public CourseResponseDto setTeacherToCourse(Integer courseId, TeacherEntity teacher) {

    Course course = repository.findById(courseId).orElseThrow(
        () -> new NoSuchElementException("Could not find course: [" + courseId + "]"));
    course.setTeacher(teacher);

    return mapper.toCourseResponseDto(repository.save(course));
  }

  public CourseResponseDto addStudentToCourse(Integer courseId, StudentEntity student) {

    Course course = repository.findById(courseId).orElseThrow(
        () -> new NoSuchElementException("Could not find course: [" + courseId + "]"));
    course.addStudents(List.of(student));

    return mapper.toCourseResponseDto(repository.save(course));

  }

  public CourseResponseDto addCommentToCourse(Integer courseId, CommentGatewayResponseDto commentGatewayResponseDto) {

    Comment comment = mapper.toCommentEntity(commentGatewayResponseDto.getCommentRequestDto());
    comment.setUsername(commentGatewayResponseDto.getUsername());
    comment.setId(sequenceGeneratorService.generateSequence(Comment.SEQUENCE_NAME));
    Course course = repository.findById(courseId).orElseThrow(
        () -> new NoSuchElementException("Could not find course: [" + courseId + "]"));
    course.addComments(List.of(comment));

    return mapper.toCourseResponseDto(repository.save(course));
  }

  public CourseResponseDto addMarkToCourse(Integer courseId, MarkGatewayResponseDto markGatewayResponseDto) {

    Mark mark = mapper.toMarkEntity(markGatewayResponseDto.getMarkRequestDto());
    mark.setStudentName(markGatewayResponseDto.getStudentName());
    mark.setId(sequenceGeneratorService.generateSequence(Comment.SEQUENCE_NAME));
    Course course = repository.findById(courseId).orElseThrow(
        () -> new NoSuchElementException("Could not find course: [" + courseId + "]"));
    course.addMarks(List.of(mark));

    return mapper.toCourseResponseDto(repository.save(course));
  }

  public CourseResponseDto addLessonToCourse(Integer id, LessonGatewayResponseDto lessonGatewayResponseDto) {

    Lesson lesson = mapper.toLessonEntity(lessonGatewayResponseDto.getLessonRequestDto());
    lesson.setId(sequenceGeneratorService.generateSequence(Comment.SEQUENCE_NAME));
    lesson.addStudents(lessonGatewayResponseDto.getStudentEntities());
    Course course = repository.findById(id).orElseThrow(
        () -> new NoSuchElementException("Could not find course: [" + id + "]"));
    course.addLessons(List.of(lesson));
    lessonRepository.save(lesson);

    return mapper.toCourseResponseDto(repository.save(course));
  }
}
