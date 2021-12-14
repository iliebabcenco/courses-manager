package md.ilie.coursesmanager.gateway.controller;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.CourseEntity;
import md.ilie.coursesmanager.gateway.client.EducationServiceClient;
import md.ilie.coursesmanager.gateway.client.UserServiceClient;
import md.ilie.coursesmanager.gateway.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class MainController {

  private final ManagerService service;

  @GetMapping("/courses")
  public ResponseEntity<List<CourseEntity>> getAllCourses() {
    return service.findAllCourses();
  }

  @PostMapping("/courses")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<CourseEntity> createCourse(@RequestBody CourseEntity course) {
    return service.createCourse(course);
  }

  @GetMapping
  public ResponseEntity<CourseEntity> getCourse(@RequestBody CourseEntity course) {
    return service.createCourse(course);
  }

  @GetMapping("/users/{id}/courses")
  public ResponseEntity<List<CourseEntity>> findCoursesByUserId(@PathVariable("id") int userId) {
    return ResponseEntity.ok(service.findUserCourses(userId));
  }

}
