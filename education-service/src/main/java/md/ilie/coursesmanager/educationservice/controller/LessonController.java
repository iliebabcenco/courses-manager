package md.ilie.coursesmanager.educationservice.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Comment;
import md.ilie.coursesmanager.educationservice.entity.Lesson;
import md.ilie.coursesmanager.educationservice.entity.dto.LessonDto;
import md.ilie.coursesmanager.educationservice.service.LessonService;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lessons")
@AllArgsConstructor
public class LessonController {

  private final LessonService lessonService;

  @PostMapping
  public ResponseEntity<Lesson> create(@RequestBody Lesson lessonEntity) {

    Lesson createdLessonEntity = lessonService.save(lessonEntity);

    return ResponseEntity.ok(createdLessonEntity);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Lesson> update(@PathVariable int id, @RequestBody Lesson lessonEntity) {

    Lesson updatedLessonEntity = lessonService.update(id, lessonEntity);

    return ResponseEntity.ok(updatedLessonEntity);
  }

  @GetMapping
  public ResponseEntity<List<Lesson>> findAll() {

    return ResponseEntity.ok(lessonService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Lesson> findById(@PathVariable int id) {

    return ResponseEntity.ok(lessonService.findById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> remove(@PathVariable int id) {

    lessonService.delete(id);
    return ResponseEntity.ok("Successfully deleted");
  }

  @PatchMapping("/{lessonId}/student")
  public ResponseEntity<LessonDto> addStudentToLesson(@PathVariable("lessonId") Integer lessonId,
      @RequestBody List<StudentEntity> students) {

    return ResponseEntity.ok(lessonService.addStudentsToLesson(lessonId, students));
  }

  @PatchMapping("/{lessonId}/comment")
  public ResponseEntity<LessonDto> addCommentToLesson(@PathVariable("lessonId") Integer lessonId,
      @RequestBody Comment comment) {

    return ResponseEntity.ok(lessonService.addCommentToLesson(lessonId, comment));
  }

}
