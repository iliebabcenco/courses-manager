package md.ilie.coursesmanager.educationservice.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.dto.request.CommentRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.request.LessonRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.LessonResponseDto;
import md.ilie.coursesmanager.educationservice.service.LessonService;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lessons")
@AllArgsConstructor
public class LessonController {

  private final LessonService lessonService;

  @PostMapping
  public ResponseEntity<LessonResponseDto> create(@RequestBody LessonRequestDto lessonEntity) {

    return ResponseEntity.ok(lessonService.save(lessonEntity));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<LessonResponseDto> update(@PathVariable int id,
      @RequestBody LessonRequestDto lessonEntity) {

    return ResponseEntity.ok(lessonService.update(id, lessonEntity));
  }

  @GetMapping
  public ResponseEntity<List<LessonResponseDto>> findAll() {

    return ResponseEntity.ok(lessonService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<LessonResponseDto> findById(@PathVariable int id) {

    return ResponseEntity.ok(lessonService.findById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> remove(@PathVariable int id) {

    lessonService.delete(id);
    return ResponseEntity.ok("Successfully deleted");
  }

  @PatchMapping("/{lessonId}/student")
  public ResponseEntity<LessonResponseDto> addStudentToLesson(@PathVariable("lessonId") Integer lessonId,
      @RequestBody List<StudentEntity> students) {

    return ResponseEntity.ok(lessonService.addStudentsToLesson(lessonId, students));
  }

  @PatchMapping("/{lessonId}/comment")
  public ResponseEntity<LessonResponseDto> addCommentToLesson(@PathVariable("lessonId") Integer lessonId,
      @RequestBody CommentRequestDto comment) {

    return ResponseEntity.ok(lessonService.addCommentToLesson(lessonId, comment));
  }

}
