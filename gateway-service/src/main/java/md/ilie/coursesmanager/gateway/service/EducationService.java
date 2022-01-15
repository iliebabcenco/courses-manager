package md.ilie.coursesmanager.gateway.service;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.dto.CourseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.LessonDto;
import md.ilie.coursesmanager.gateway.client.EducationServiceClient;
import md.ilie.coursesmanager.gateway.client.UserServiceClient;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import md.ilie.coursesmanager.userservice.entity.TeacherEntity;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EducationService {

  private final EducationServiceClient educationServiceClient;
  private final UserServiceClient userServiceClient;

  public ResponseEntity<List<CourseDto>> findAll() {

    return educationServiceClient.findAllCourses();
  }

  public ResponseEntity<CourseDto> findById(int id) {

    return educationServiceClient.findCourseById(id);
  }

  public ResponseEntity<CourseDto> create(Course courseEntity) {

    return educationServiceClient.createCourse(courseEntity);
  }

  public ResponseEntity<String> delete(int id) {

    return educationServiceClient.remove(id);
  }

  public ResponseEntity<CourseDto> update(int id, Course courseEntity) {

    return educationServiceClient.update(id, courseEntity);
  }

  public ResponseEntity<List<CourseDto>> getCoursesByUserId(Integer id) {

    return educationServiceClient.getCoursesByUserId(id);
  }

  public ResponseEntity<CourseDto> setTeacherToCourse(Integer courseId, Integer teacherId) {

    UserEntityDto user = userServiceClient.findById(teacherId).getBody();
    if (user == null) {
      throw new NoSuchElementException("Could not find teacher: [" + teacherId + "]");
    }
    TeacherEntity teacher = new TeacherEntity(user);

    return educationServiceClient.setTeacherToCourse(courseId, teacher);
  }

  public ResponseEntity<CourseDto> addStudentToCourse(Integer courseId, Integer studentId) {

    UserEntityDto user = userServiceClient.findById(studentId).getBody();
    if (user == null) {
      throw new NoSuchElementException("Could not find student: [" + studentId + "]");
    }
    StudentEntity student = new StudentEntity(user);

    return educationServiceClient.addStudentToCourse(courseId, student);
  }

  public ResponseEntity<List<LessonDto>> getLessonsByUserId(int userId) {

    return null;

  }
}
