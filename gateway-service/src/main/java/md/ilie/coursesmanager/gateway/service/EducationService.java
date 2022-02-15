package md.ilie.coursesmanager.gateway.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse.CommentGatewayResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse.CourseGatewayResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse.LessonGatewayResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse.MarkGatewayResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.request.CommentRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.request.CourseRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.request.LessonRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.request.MarkRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.CourseResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.LessonResponseDto;
import md.ilie.coursesmanager.gateway.client.EducationServiceClient;
import md.ilie.coursesmanager.gateway.client.UserServiceClient;
import md.ilie.coursesmanager.userservice.entity.RoleEnum;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import md.ilie.coursesmanager.userservice.entity.TeacherEntity;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EducationService {

  private final EducationServiceClient educationServiceClient;
  private final UserServiceClient userServiceClient;

  public List<CourseResponseDto> findAll() {

    return educationServiceClient.findAllCourses().getBody();
  }

  public CourseResponseDto findById(int id) {

    return educationServiceClient.findCourseById(id).getBody();
  }

  public CourseResponseDto create(CourseRequestDto courseEntity) {

    List<StudentEntity> studentEntities = toStudentsList(
        Objects.requireNonNull(userServiceClient.getAllUsersByIds(courseEntity.getStudentsIds()).getBody()));
    TeacherEntity teacherEntity = new TeacherEntity(Objects.requireNonNull(
        userServiceClient.findById(courseEntity.getTeacherId()).getBody()));
    CourseGatewayResponseDto courseGatewayResponseDto = new CourseGatewayResponseDto(courseEntity, studentEntities,
        teacherEntity);
    return educationServiceClient.createCourse(courseGatewayResponseDto).getBody();
  }

  public String delete(int id) {

    return educationServiceClient.remove(id).getBody();
  }

  public CourseResponseDto update(int id, CourseRequestDto courseEntity) {

    List<StudentEntity> studentEntities = toStudentsList(
        Objects.requireNonNull(userServiceClient.getAllUsersByIds(courseEntity.getStudentsIds()).getBody()));
    TeacherEntity teacherEntity = new TeacherEntity(Objects.requireNonNull(
        userServiceClient.findById(courseEntity.getTeacherId()).getBody()));
    CourseGatewayResponseDto courseGatewayResponseDto = new CourseGatewayResponseDto(courseEntity, studentEntities,
        teacherEntity);
    return educationServiceClient.update(id, courseGatewayResponseDto).getBody();
  }

  public List<CourseResponseDto> getCoursesByUserId(Integer id) {

    return educationServiceClient.getCoursesByUserId(id).getBody();
  }

  public CourseResponseDto setTeacherToCourse(Integer courseId, Integer teacherId) {

    UserEntityDto user = userServiceClient.findById(teacherId).getBody();
    if (user == null) {
      throw new NoSuchElementException("Could not find teacher: [" + teacherId + "]");
    }
    userServiceClient.updateUserRoles(user.getId(), List.of(RoleEnum.MANAGER));
    TeacherEntity teacher = new TeacherEntity(user);

    return educationServiceClient.setTeacherToCourse(courseId, teacher).getBody();
  }

  public CourseResponseDto addStudentToCourse(Integer courseId, Integer studentId) {

    UserEntityDto user = userServiceClient.findById(studentId).getBody();
    if (user == null) {
      throw new NoSuchElementException("Could not find student: [" + studentId + "]");
    }
    StudentEntity student = new StudentEntity(user);

    return educationServiceClient.addStudentToCourse(courseId, student).getBody();
  }

  public CourseResponseDto addMarkToCourse(Integer courseId, MarkRequestDto mark) {

    Integer userId = mark.getStudentId();
    UserEntityDto user = userServiceClient.findById(userId).getBody();
    if (user == null) {
      throw new NoSuchElementException("Could not find student: [" + userId + "]");
    }
    MarkGatewayResponseDto markGatewayResponseDto = new MarkGatewayResponseDto(mark, user.getUsername());

    return educationServiceClient.addMarkToCourse(courseId, markGatewayResponseDto).getBody();
  }

  public CourseResponseDto addCommentToCourse(Integer courseId, CommentRequestDto comment) {

    Integer userId = comment.getUserId();
    UserEntityDto user = userServiceClient.findById(userId).getBody();
    if (user == null) {
      throw new NoSuchElementException("Could not find student: [" + userId + "]");
    }
    CommentGatewayResponseDto commentGatewayResponseDto = new CommentGatewayResponseDto(comment, user.getUsername());

    return educationServiceClient.addCommentToCourse(courseId, commentGatewayResponseDto).getBody();
  }

  public CourseResponseDto addLessonToCourse(Integer courseId, LessonRequestDto lesson) {

    List<StudentEntity> studentEntities = toStudentsList(
        Objects.requireNonNull(userServiceClient.getAllUsersByIds(lesson.getStudentsIds()).getBody()));

    LessonGatewayResponseDto lessonGatewayResponseDto = new LessonGatewayResponseDto(lesson, studentEntities);

    return educationServiceClient.addLessonToCourse(courseId, lessonGatewayResponseDto).getBody();
  }

  public List<LessonResponseDto> getLessonsByUserId(int userId) {

    if (userServiceClient.findById(userId).getBody() == null) {
      throw new NoSuchElementException("Could not find user: [" + userId + "]");
    }

    return educationServiceClient.getLessonsByUserId(userId).getBody();
  }

  public LessonResponseDto addStudentToLesson(Integer lessonId, Integer studentId) {

    UserEntityDto user = userServiceClient.findById(studentId).getBody();
    if (user == null) {
      throw new NoSuchElementException("Could not find student: [" + studentId + "]");
    }
    StudentEntity student = new StudentEntity(user);

    return educationServiceClient.addStudentToLesson(lessonId, List.of(student)).getBody();
  }

  public LessonResponseDto addCommentToLesson(Integer lessonId, CommentRequestDto comment) {

    Integer userId = comment.getUserId();
    UserEntityDto user = userServiceClient.findById(userId).getBody();
    if (user == null) {
      throw new NoSuchElementException("Could not find user: [" + userId + "]");
    }
    CommentGatewayResponseDto commentGatewayResponseDto = new CommentGatewayResponseDto(comment, user.getUsername());

    return educationServiceClient.addCommentToLesson(lessonId, commentGatewayResponseDto).getBody();
  }

  public static List<StudentEntity> toStudentsList(List<UserEntityDto> userEntityDtoList) {

    return userEntityDtoList.stream().map(StudentEntity::new).collect(Collectors.toList());
  }

}
