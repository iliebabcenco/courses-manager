package md.ilie.coursesmanager.gateway.controller;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.dto.CourseDto;
import md.ilie.coursesmanager.gateway.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/courses")
@RestController
public class CourseController {

  private CourseService courseService;

  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @PostMapping
  public ResponseEntity<CourseDto> create(@RequestBody Course courseEntity) {

    return courseService.create(courseEntity);
  }

  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @PatchMapping("/{id}")
  public ResponseEntity<CourseDto> update(@PathVariable int id, @RequestBody Course courseEntity) {

    return courseService.update(id, courseEntity);
  }

  @GetMapping
  public ResponseEntity<List<CourseDto>> findAll() {

    return courseService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<CourseDto> findById(@PathVariable int id) {

    return courseService.findById(id);
  }

  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> remove(@PathVariable int id) {

    return courseService.delete(id);
  }

  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @PatchMapping("/{courseId}/teacher/{teacherId}")
  public ResponseEntity<CourseDto> setTeacherToCourse(@PathVariable Integer courseId, @PathVariable Integer teacherId) {

    return courseService.setTeacherToCourse(courseId, teacherId);
  }

  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @PatchMapping("/{courseId}/student/{studentId}")
  public ResponseEntity<CourseDto> addStudentToCourse(@PathVariable Integer courseId, @PathVariable Integer studentId) {

    return courseService.addStudentToCourse(courseId, studentId);
  }


}
