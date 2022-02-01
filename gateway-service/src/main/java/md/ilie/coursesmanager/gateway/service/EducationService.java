package md.ilie.coursesmanager.gateway.service;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.dto.request.CommentRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.request.CourseRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.request.LessonRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.request.MarkRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.CourseResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.LessonResponseDto;
import md.ilie.coursesmanager.gateway.client.EducationServiceClient;
import md.ilie.coursesmanager.gateway.client.UserServiceClient;
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

    return educationServiceClient.createCourse(courseEntity).getBody();
  }

  public String delete(int id) {

    return educationServiceClient.remove(id).getBody();
  }

  public CourseResponseDto update(int id, CourseRequestDto courseEntity) {

    return educationServiceClient.update(id, courseEntity).getBody();
  }

  public List<CourseResponseDto> getCoursesByUserId(Integer id) {

    return educationServiceClient.getCoursesByUserId(id).getBody();
  }

  public CourseResponseDto setTeacherToCourse(Integer courseId, Integer teacherId) {

    UserEntityDto user = userServiceClient.findById(teacherId).getBody();
    if (user == null) {
      throw new NoSuchElementException("Could not find teacher: [" + teacherId + "]");
    }
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

    Integer studentId = mark.getStudentId();
    if (userServiceClient.findById(studentId).getBody() == null) {
      throw new NoSuchElementException("Could not find student: [" + studentId + "]");
    }
    return educationServiceClient.addMarkToCourse(courseId, mark).getBody();
  }

  public CourseResponseDto addCommentToCourse(Integer courseId, CommentRequestDto comment) {

    Integer userId = comment.getUserId();
    if (userServiceClient.findById(userId).getBody() == null) {
      throw new NoSuchElementException("Could not find student: [" + userId + "]");
    }
    return educationServiceClient.addCommentToCourse(courseId, comment).getBody();
  }

  public CourseResponseDto addLessonToCourse(Integer courseId, LessonRequestDto lesson) {

    List<Integer> userIds = lesson.getStudentsIds();
    userIds.add(lesson.getTeacherId());
    if (!allUsersExistByIds(userIds)) {
      throw new NoSuchElementException("Some users could not be found in db");
    }
    return educationServiceClient.addLessonToCourse(courseId, lesson).getBody();
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
    return educationServiceClient.addCommentToLesson(lessonId, comment).getBody();
  }

  public Boolean allUsersExistByIds(List<Integer> ids) {

    return userServiceClient.checkAllUsersExistById(ids).getBody();
  }

}
