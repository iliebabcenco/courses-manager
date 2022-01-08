package md.ilie.coursesmanager.educationservice.controller;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.dto.CourseDto;
import md.ilie.coursesmanager.educationservice.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class CourseController {

  private final CourseService courseService;

  @PostMapping("/courses")
  public ResponseEntity<CourseDto> create(@RequestBody Course courseEntity) {

    return ResponseEntity.ok(courseService.save(courseEntity));
  }

  @PatchMapping("/courses/{id}")
  public ResponseEntity<CourseDto> update(@PathVariable int id, @RequestBody Course courseEntity) {

    return ResponseEntity.ok(courseService.update(id, courseEntity));
  }

  @GetMapping("/courses")
  public ResponseEntity<List<CourseDto>> findAll() {

    return ResponseEntity.ok(courseService.findAll());
  }

  @GetMapping("/courses/{id}")
  public ResponseEntity<CourseDto> findById(@PathVariable int id) {

    return ResponseEntity.ok(courseService.findById(id));
  }

  @DeleteMapping("/courses/{id}")
  public ResponseEntity<String> remove(@PathVariable int id) {
    courseService.delete(id);

    return ResponseEntity.ok("Successfully deleted");
  }

  @GetMapping("/users/{id}/courses")
  public ResponseEntity<List<CourseDto>> getCoursesByUserId(@PathVariable int id) {

    return ResponseEntity.ok(courseService.getCoursesByUserId(id));
  }

}
