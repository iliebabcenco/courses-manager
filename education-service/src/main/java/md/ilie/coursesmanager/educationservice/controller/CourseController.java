package md.ilie.coursesmanager.educationservice.controller;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse.CommentGatewayResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse.CourseGatewayResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse.LessonGatewayResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse.MarkGatewayResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.CourseResponseDto;
import md.ilie.coursesmanager.educationservice.service.CourseService;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import md.ilie.coursesmanager.userservice.entity.TeacherEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CourseController {

  private final CourseService courseService;

  @PostMapping("/courses")
  public ResponseEntity<CourseResponseDto> create(@RequestBody CourseGatewayResponseDto courseRequestDto) {

    return ResponseEntity.ok(courseService.save(courseRequestDto));
  }

  @PatchMapping("/courses/{id}")
  public ResponseEntity<CourseResponseDto> update(@PathVariable("id") int id,
                                                  @RequestBody CourseGatewayResponseDto courseRequestDto) {

    return ResponseEntity.ok(courseService.update(id, courseRequestDto));
  }

  @GetMapping("/courses")
  public ResponseEntity<List<CourseResponseDto>> findAll() {

    return ResponseEntity.ok(courseService.findAll());
  }

  @GetMapping("/courses/{id}")
  public ResponseEntity<CourseResponseDto> findById(@PathVariable("id") int id) {

    return ResponseEntity.ok(courseService.findById(id));
  }

  @DeleteMapping("/courses/{id}")
  public ResponseEntity<String> remove(@PathVariable("id") int id) {
    courseService.delete(id);

    return ResponseEntity.ok("Successfully deleted");
  }

  @PatchMapping("/courses/{courseId}/teacher")
  public ResponseEntity<CourseResponseDto> setTeacherToCourse(@PathVariable("courseId") Integer courseId,
                                                              @RequestBody TeacherEntity teacher) {

    return ResponseEntity.ok(courseService.setTeacherToCourse(courseId, teacher));
  }

  @PatchMapping("/courses/{courseId}/student")
  public ResponseEntity<CourseResponseDto> addStudentToCourse(@PathVariable("courseId") Integer courseId,
                                                              @RequestBody StudentEntity student) {

    return ResponseEntity.ok(courseService.addStudentToCourse(courseId, student));
  }

  @PatchMapping("/courses/{courseId}/comment")
  public ResponseEntity<CourseResponseDto> addCommentToCourse(@PathVariable("courseId") Integer courseId,
                                                              @RequestBody CommentGatewayResponseDto comment) {

    return ResponseEntity.ok(courseService.addCommentToCourse(courseId, comment));
  }

  @PatchMapping("/courses/{courseId}/mark")
  public ResponseEntity<CourseResponseDto> addMarkToCourse(@PathVariable("courseId") Integer courseId,
                                                           @RequestBody MarkGatewayResponseDto mark) {

    return ResponseEntity.ok(courseService.addMarkToCourse(courseId, mark));
  }

  @PatchMapping("/courses/{courseId}/lesson")
  public ResponseEntity<CourseResponseDto> addLessonToCourse(@PathVariable("courseId") Integer courseId,
                                                             @RequestBody LessonGatewayResponseDto lesson) {

    return ResponseEntity.ok(courseService.addLessonToCourse(courseId, lesson));
  }

}
