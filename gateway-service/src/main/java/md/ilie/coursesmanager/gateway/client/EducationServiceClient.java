package md.ilie.coursesmanager.gateway.client;

import md.ilie.coursesmanager.educationservice.entity.CourseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name = "education-service", url = "localhost:8083")
public interface EducationServiceClient {

  @GetMapping("/courses")
  ResponseEntity<List<CourseEntity>> findAllCourses();

}
