package md.ilie.coursesmanager.gateway.service;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Comment;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.Mark;
import md.ilie.coursesmanager.educationservice.entity.dto.CourseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.LessonDto;
import md.ilie.coursesmanager.gateway.client.EducationServiceClient;
import md.ilie.coursesmanager.gateway.client.UserServiceClient;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import md.ilie.coursesmanager.userservice.entity.TeacherEntity;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CourseService {

  private final EducationServiceClient educationServiceClient;
  private final UserServiceClient userServiceClient;

  public List<CourseDto> findAll() {

    return educationServiceClient.findAllCourses().getBody();
  }

  public CourseDto findById(int id) {

    return educationServiceClient.findCourseById(id).getBody();
  }

  public CourseDto create(Course courseEntity) {

    return educationServiceClient.createCourse(courseEntity).getBody();
  }

  public String delete(int id) {

    return educationServiceClient.remove(id).getBody();
  }

  public CourseDto update(int id, Course courseEntity) {

    return educationServiceClient.update(id, courseEntity).getBody();
  }

  public List<CourseDto> getCoursesByUserId(Integer id) {

    return educationServiceClient.getCoursesByUserId(id).getBody();
  }

  public CourseDto setTeacherToCourse(Integer courseId, Integer teacherId) {

    UserEntityDto user = userServiceClient.findById(teacherId).getBody();
    if (user == null) {
      throw new NoSuchElementException("Could not find teacher: [" + teacherId + "]");
    }
    TeacherEntity teacher = new TeacherEntity(user);

    return educationServiceClient.setTeacherToCourse(courseId, teacher).getBody();
  }

  public CourseDto addStudentToCourse(Integer courseId, Integer studentId) {

    UserEntityDto user = userServiceClient.findById(studentId).getBody();
    if (user == null) {
      throw new NoSuchElementException("Could not find student: [" + studentId + "]");
    }
    StudentEntity student = new StudentEntity(user);

    return educationServiceClient.addStudentToCourse(courseId, student).getBody();
  }

  public CourseDto addMarkToCourse(Integer courseId, Mark mark) {

    return educationServiceClient.addMarkToCourse(courseId, mark).getBody();
  }

  public CourseDto addCommentToCourse(Integer courseId, Comment comment) {

    return educationServiceClient.addCommentToCourse(courseId, comment).getBody();
  }

  public List<LessonDto> getLessonsByUserId(int userId) {

    return null;

  }
}
