package md.ilie.coursesmanager.educationservice.controller;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.CommentEntity;
import md.ilie.coursesmanager.educationservice.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/comments")
public class CommentController {

  private final CommentService commentService;

  @PostMapping
  public ResponseEntity<CommentEntity> create(@RequestBody CommentEntity commentEntity){

    CommentEntity createdCommentEntity = commentService.save(commentEntity);

    return ResponseEntity.ok(createdCommentEntity);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<CommentEntity> update(@PathVariable int id, @RequestBody CommentEntity commentEntity){

    CommentEntity updatedCommentEntity = commentService.update(id, commentEntity);

    return ResponseEntity.ok(updatedCommentEntity);
  }

  @GetMapping
  public ResponseEntity<List<CommentEntity>> findAll(){

    return ResponseEntity.ok(commentService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CommentEntity> findById(@PathVariable int id){

    return ResponseEntity.ok(commentService.findById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> remove(@PathVariable int id){

    commentService.delete(id);
    return ResponseEntity.ok("Successfully deleted");
  }

}
