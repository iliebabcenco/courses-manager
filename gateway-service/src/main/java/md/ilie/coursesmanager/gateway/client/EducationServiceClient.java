package md.ilie.coursesmanager.gateway.client;

import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.dto.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "education-service", url = "localhost:8083")
public interface EducationServiceClient {

  @PostMapping("/courses")
  ResponseEntity<CourseDto> createCourse(@RequestBody Course courseEntity);

  @PatchMapping("/courses/{id}")
  ResponseEntity<CourseDto> update(@PathVariable int id, @RequestBody Course courseEntity);

  @GetMapping("/courses")
  ResponseEntity<List<CourseDto>> findAllCourses();

  @GetMapping("/courses/{id}")
  ResponseEntity<CourseDto> findCourseById(@PathVariable("id") int id);

  @DeleteMapping("/courses/{id}")
  ResponseEntity<String> remove(@PathVariable int id);

  @GetMapping("/users/{id}/courses")
  ResponseEntity<List<CourseDto>> getCoursesByUserId(@PathVariable int id);

}
