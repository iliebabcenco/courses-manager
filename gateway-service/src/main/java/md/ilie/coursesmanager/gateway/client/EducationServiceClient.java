package md.ilie.coursesmanager.gateway.client;

import java.util.List;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.Lesson;
import md.ilie.coursesmanager.educationservice.entity.dto.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "education-service", url = "localhost:8083")
public interface EducationServiceClient {

  @GetMapping("/courses")
  ResponseEntity<List<CourseDto>> findAllCourses();

  @PostMapping("/courses")
  ResponseEntity<CourseDto> createCourse(@RequestBody Course courseEntity);

  @GetMapping("/courses/{id}")
  ResponseEntity<CourseDto> findCourseById(@PathVariable("id") int id);

  @GetMapping("/users/{id}/courses")
  ResponseEntity<List<CourseDto>> getCoursesByUserId(@PathVariable("id") int id);

  @GetMapping("/users/{id}/courses")
  ResponseEntity<List<Lesson>> findLessonsByUserId(@PathVariable("id") int userId);

  @GetMapping("/users/{userId}/courses/{courseId}/lessons")
  ResponseEntity<List<Lesson>> findLessonsByUserIdAndCourseId(@PathVariable("userId") int userId,
      @PathVariable("courseId") int courseId);

}
