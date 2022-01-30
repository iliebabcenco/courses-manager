package md.ilie.coursesmanager.educationservice.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Comment;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.Lesson;
import md.ilie.coursesmanager.educationservice.entity.Mark;
import md.ilie.coursesmanager.educationservice.entity.dto.CourseDto;
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
  public ResponseEntity<CourseDto> create(@RequestBody Course courseEntity) {

    return ResponseEntity.ok(courseService.save(courseEntity));
  }

  @PatchMapping("/courses/{id}")
  public ResponseEntity<CourseDto> update(@PathVariable("id") int id, @RequestBody Course courseEntity) {

    return ResponseEntity.ok(courseService.update(id, courseEntity));
  }

  @GetMapping("/courses")
  public ResponseEntity<List<CourseDto>> findAll() {

    return ResponseEntity.ok(courseService.findAll());
  }

  @GetMapping("/courses/{id}")
  public ResponseEntity<CourseDto> findById(@PathVariable("id") int id) {

    return ResponseEntity.ok(courseService.findById(id));
  }

  @DeleteMapping("/courses/{id}")
  public ResponseEntity<String> remove(@PathVariable("id") int id) {
    courseService.delete(id);

    return ResponseEntity.ok("Successfully deleted");
  }

  @PatchMapping("/courses/{courseId}/teacher")
  public ResponseEntity<CourseDto> setTeacherToCourse(@PathVariable("courseId") Integer courseId,
      @RequestBody TeacherEntity teacher) {

    return ResponseEntity.ok(courseService.setTeacherToCourse(courseId, teacher));
  }

  @PatchMapping("/courses/{courseId}/student")
  public ResponseEntity<CourseDto> addStudentToCourse(@PathVariable("courseId") Integer courseId,
      @RequestBody StudentEntity student) {

    return ResponseEntity.ok(courseService.addStudentToCourse(courseId, student));
  }

  @PatchMapping("/courses/{courseId}/comment")
  public ResponseEntity<CourseDto> addCommentToCourse(@PathVariable("courseId") Integer courseId,
      @RequestBody Comment comment) {

    return ResponseEntity.ok(courseService.addCommentToCourse(courseId, comment));
  }

  @PatchMapping("/courses/{courseId}/mark")
  public ResponseEntity<CourseDto> addMarkToCourse(@PathVariable("courseId") Integer courseId,
      @RequestBody Mark mark) {

    return ResponseEntity.ok(courseService.addMarkToCourse(courseId, mark));
  }

  @PatchMapping("/courses/{courseId}/lesson")
  public ResponseEntity<CourseDto> addLessonToCourse(@PathVariable("courseId") Integer courseId,
      @RequestBody Lesson lesson) {

    return ResponseEntity.ok(courseService.addLessonToCourse(courseId, lesson));
  }

}
