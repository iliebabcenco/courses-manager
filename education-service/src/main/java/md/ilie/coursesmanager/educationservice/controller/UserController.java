package md.ilie.coursesmanager.educationservice.controller;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.dto.response.CourseResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.LessonResponseDto;
import md.ilie.coursesmanager.educationservice.service.CourseService;
import md.ilie.coursesmanager.educationservice.service.LessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final LessonService lessonService;
  private final CourseService courseService;

  @GetMapping("/{id}/courses")
  public ResponseEntity<List<CourseResponseDto>> getCoursesByUserId(@PathVariable("id") int id) {

    return ResponseEntity.ok(courseService.getCoursesByUserId(id));
  }

  @GetMapping("/{id}/lessons")
  public ResponseEntity<List<LessonResponseDto>> getLessonsByUserId(@PathVariable("id") int id) {

    return ResponseEntity.ok(lessonService.getLessonsByUserId(id));
  }

}
