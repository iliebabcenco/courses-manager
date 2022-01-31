package md.ilie.coursesmanager.gateway.service;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Comment;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.Lesson;
import md.ilie.coursesmanager.educationservice.entity.Mark;
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

  public CourseResponseDto create(Course courseEntity) {

    return educationServiceClient.createCourse(courseEntity).getBody();
  }

  public String delete(int id) {

    return educationServiceClient.remove(id).getBody();
  }

  public CourseResponseDto update(int id, Course courseEntity) {

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

  public CourseResponseDto addMarkToCourse(Integer courseId, Mark mark) {

    return educationServiceClient.addMarkToCourse(courseId, mark).getBody();
  }

  public CourseResponseDto addCommentToCourse(Integer courseId, Comment comment) {

    return educationServiceClient.addCommentToCourse(courseId, comment).getBody();
  }

  public CourseResponseDto addLessonToCourse(Integer courseId, Lesson lesson) {

    return educationServiceClient.addLessonToCourse(courseId, lesson).getBody();
  }

  public List<LessonResponseDto> getLessonsByUserId(int userId) {

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

  public LessonResponseDto addCommentToLesson(Integer lessonId, Comment comment) {

    return educationServiceClient.addCommentToLesson(lessonId, comment).getBody();
  }

}
