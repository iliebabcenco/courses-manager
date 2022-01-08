package md.ilie.coursesmanager.gateway.service;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.dto.CourseDto;
import md.ilie.coursesmanager.gateway.client.EducationServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CourseService {

  private final EducationServiceClient educationServiceClient;

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


}
