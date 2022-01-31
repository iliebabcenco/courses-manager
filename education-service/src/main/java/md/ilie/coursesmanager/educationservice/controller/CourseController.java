package md.ilie.coursesmanager.educationservice.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Comment;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.Lesson;
import md.ilie.coursesmanager.educationservice.entity.Mark;
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

@RestController
@AllArgsConstructor
public class CourseController {

  private final CourseService courseService;

  @PostMapping("/courses")
  public ResponseEntity<CourseResponseDto> create(@RequestBody Course courseEntity) {

    return ResponseEntity.ok(courseService.save(courseEntity));
  }

  @PatchMapping("/courses/{id}")
  public ResponseEntity<CourseResponseDto> update(@PathVariable("id") int id, @RequestBody Course courseEntity) {

    return ResponseEntity.ok(courseService.update(id, courseEntity));
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
      @RequestBody Comment comment) {

    return ResponseEntity.ok(courseService.addCommentToCourse(courseId, comment));
  }

  @PatchMapping("/courses/{courseId}/mark")
  public ResponseEntity<CourseResponseDto> addMarkToCourse(@PathVariable("courseId") Integer courseId,
      @RequestBody Mark mark) {

    return ResponseEntity.ok(courseService.addMarkToCourse(courseId, mark));
  }

  @PatchMapping("/courses/{courseId}/lesson")
  public ResponseEntity<CourseResponseDto> addLessonToCourse(@PathVariable("courseId") Integer courseId,
      @RequestBody Lesson lesson) {

    return ResponseEntity.ok(courseService.addLessonToCourse(courseId, lesson));
  }

}
