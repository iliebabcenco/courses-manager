package md.ilie.coursesmanager.gateway.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.dto.CourseDto;
import md.ilie.coursesmanager.gateway.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class CourseController {

  private CourseService courseService;

  @GetMapping("/courses")
  public ResponseEntity<List<CourseDto>> getAllCourses() {

    return courseService.findAllCourses();
  }

  @PreAuthorize("#userId == authentication.principal.id")
  @GetMapping("/users/{id}/courses")
  public ResponseEntity<List<CourseDto>> getUserCourses(@PathVariable("id") int userId) {

    return ResponseEntity.ok(courseService.findUserCourses(userId));
  }

  @PostMapping("/courses")
  @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
  public ResponseEntity<CourseDto> createCourse(@RequestBody Course course) {

    return courseService.createCourse(course);
  }

  @GetMapping
  public ResponseEntity<CourseDto> getCourse(@RequestBody Course course) {

    return courseService.createCourse(course);
  }

}
