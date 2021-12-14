package md.ilie.coursesmanager.gateway.client;

import md.ilie.coursesmanager.educationservice.entity.CourseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "education-service", url = "localhost:8083")
public interface EducationServiceClient {

  @GetMapping("/courses")
  ResponseEntity<List<CourseEntity>> findAllCourses();

  @PostMapping("/courses")
  ResponseEntity<CourseEntity> createCourse(@RequestBody CourseEntity courseEntity);

  @GetMapping("/courses/{id}")
  ResponseEntity<CourseEntity> findCourseById(@PathVariable("id") int id);

  @GetMapping("/users/{id}/courses")
  ResponseEntity<List<CourseEntity>> getCoursesByUserId(@PathVariable("id") int id);

}
