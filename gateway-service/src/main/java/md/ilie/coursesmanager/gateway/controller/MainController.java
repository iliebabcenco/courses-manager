package md.ilie.coursesmanager.gateway.controller;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.CourseEntity;
import md.ilie.coursesmanager.educationservice.entity.LessonEntity;
import md.ilie.coursesmanager.gateway.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
  @PreAuthorize("hasAuthority('MANAGER')")
  public ResponseEntity<CourseEntity> createCourse(@RequestBody CourseEntity course) {
    return service.createCourse(course);
  }

  @GetMapping
  public ResponseEntity<CourseEntity> getCourse(@RequestBody CourseEntity course) {
    return service.createCourse(course);
  }

  @PreAuthorize("#userId == authentication.principal.id")
  @GetMapping("/users/{id}/courses")
  public ResponseEntity<List<CourseEntity>> findCoursesByUserId(@PathVariable("id") int userId) {
    return ResponseEntity.ok(service.findUserCourses(userId));
  }

  @PreAuthorize("#userId == authentication.principal.id")
  @GetMapping("/users/{id}/lessons")
  public ResponseEntity<List<LessonEntity>> findLessonsByUserId(@PathVariable("id") int userId) {
    return ResponseEntity.ok(service.findLessonsByUserId(userId));
  }

  @PreAuthorize("#userId == authentication.principal.id")
  @GetMapping("/users/{userId}/courses/{courseId}/lessons")
  public ResponseEntity<List<LessonEntity>> findLessonsFromCourseByUserId(@PathVariable("userId") int userId,
      @PathVariable("courseId") int courseId) {
    return ResponseEntity.ok(service.findLessonsByUserIdAndCourseId(userId, courseId));
  }

}
