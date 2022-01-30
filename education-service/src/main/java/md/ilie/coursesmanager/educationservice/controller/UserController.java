package md.ilie.coursesmanager.educationservice.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.dto.CourseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.LessonDto;
import md.ilie.coursesmanager.educationservice.service.CourseService;
import md.ilie.coursesmanager.educationservice.service.LessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final LessonService lessonService;
  private final CourseService courseService;

  @GetMapping("/{id}/courses")
  public ResponseEntity<List<CourseDto>> getCoursesByUserId(@PathVariable("id") int id) {

    return ResponseEntity.ok(courseService.getCoursesByUserId(id));
  }

  @GetMapping("/{id}/lessons")
  public ResponseEntity<List<LessonDto>> getLessonsByUserId(@PathVariable("id") int id) {

    return ResponseEntity.ok(lessonService.getLessonsByUserId(id));
  }

}
