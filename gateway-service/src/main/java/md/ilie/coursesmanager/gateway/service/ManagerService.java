package md.ilie.coursesmanager.gateway.service;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.CourseEntity;
import md.ilie.coursesmanager.educationservice.entity.LessonEntity;
import md.ilie.coursesmanager.gateway.client.EducationServiceClient;
import md.ilie.coursesmanager.gateway.client.UserServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ManagerService {

  private final EducationServiceClient educationServiceClient;
  private final UserServiceClient userServiceClient;

  public ResponseEntity<List<CourseEntity>> findAllCourses() {

    return educationServiceClient.findAllCourses();
  }

  public ResponseEntity<CourseEntity> createCourse(CourseEntity course) {

    return educationServiceClient.createCourse(course);
  }

  public List<CourseEntity> findUserCourses(int userId) {

    List<CourseEntity> userCourses = new ArrayList<>();

    if (userServiceClient.findById(userId).getBody() != null) {
      userCourses = educationServiceClient.getCoursesByUserId(userId).getBody();
    }

    return userCourses;
  }

  public List<LessonEntity> findLessonsByUserId(int userId) {

    return educationServiceClient.findLessonsByUserId(userId).getBody();
  }

  public List<LessonEntity> findLessonsByUserIdAndCourseId(int userId, int courseId) {

    return educationServiceClient.findLessonsByUserIdAndCourseId(userId, courseId).getBody();
  }
}
