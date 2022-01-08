package md.ilie.coursesmanager.gateway.controller;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.dto.CourseDto;
import md.ilie.coursesmanager.gateway.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class UserCourseController {

  private CourseService courseService;

  @PreAuthorize("#userId == authentication.principal.id")
  @GetMapping("/users/{id}/courses")
  public ResponseEntity<List<CourseDto>> getUserCourses(@PathVariable("id") int userId) {

    return courseService.getCoursesByUserId(userId);
  }

}
