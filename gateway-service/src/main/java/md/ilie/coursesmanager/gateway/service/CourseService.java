package md.ilie.coursesmanager.gateway.service;

import java.util.List;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.dto.CourseDto;
import md.ilie.coursesmanager.gateway.client.EducationServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CourseService {

  private final EducationServiceClient educationServiceClient;

  public List<CourseDto> findUserCourses(int userId) {

    return educationServiceClient.getCoursesByUserId(userId).getBody();
  }

  public ResponseEntity<List<CourseDto>> findAllCourses() {

    return educationServiceClient.findAllCourses();
  }

  public ResponseEntity<CourseDto> createCourse(Course course) {

    return educationServiceClient.createCourse(course);
  }


}
