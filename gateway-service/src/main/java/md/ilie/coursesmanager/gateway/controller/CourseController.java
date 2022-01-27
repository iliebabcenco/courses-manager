package md.ilie.coursesmanager.gateway.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Comment;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.Mark;
import md.ilie.coursesmanager.educationservice.entity.dto.CourseDto;
import md.ilie.coursesmanager.gateway.service.CourseService;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@RequestMapping("/courses")
@RestController
public class CourseController {

  private final CourseService courseService;

  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @PostMapping
  public ResponseEntity<CourseDto> create(@RequestBody Course courseEntity) {
    try {
      return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(courseService.create(courseEntity));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while registering new user!", e);
    }
  }

  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @PatchMapping("/{id}")
  public ResponseEntity<CourseDto> update(@PathVariable("id") int id, @RequestBody Course courseEntity) {
    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(courseService.update(id, courseEntity));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while registering new user!", e);
    }
  }

  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  @GetMapping
  public ResponseEntity<List<CourseDto>> findAll() {
    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(courseService.findAll());
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while getting all courses!", e);
    }
  }

  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  @GetMapping("/{id}")
  public ResponseEntity<CourseDto> findById(@PathVariable("id") int id) {
    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(courseService.findById(id));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while getting course by id!", e);
    }
  }

  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> remove(@PathVariable("id") int id) {
    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(courseService.delete(id));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while removing course by id!", e);
    }
  }

  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @PatchMapping("/{courseId}/teacher/{teacherId}")
  public ResponseEntity<CourseDto> setTeacherToCourse(@PathVariable("courseId") Integer courseId,
      @PathVariable("teacherId") Integer teacherId) {
    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(courseService.setTeacherToCourse(courseId, teacherId));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while setting teacher to course", e);
    }
  }

  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @PatchMapping("/{courseId}/student/{studentId}")
  public ResponseEntity<CourseDto> addStudentToCourse(@PathVariable("courseId") Integer courseId,
      @PathVariable("studentId") Integer studentId) {
    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(courseService.addStudentToCourse(courseId, studentId));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while adding student to course", e);
    }
  }

  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @PatchMapping("/courses/{courseId}/mark")
  public ResponseEntity<CourseDto> addMarkToCourse(@PathVariable("courseId") Integer courseId, @RequestBody Mark mark) {
    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(courseService.addMarkToCourse(courseId, mark));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while adding mark to course", e);
    }
  }

  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  @PatchMapping("/courses/{courseId}/mark")
  public ResponseEntity<CourseDto> addCommentToCourse(@PathVariable("courseId") Integer courseId, @RequestBody
      Comment comment) {
    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(courseService.addCommentToCourse(courseId, comment));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while adding comment to course", e);
    }
  }

}
