package md.ilie.coursesmanager.educationservice.controller;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Mark;
import md.ilie.coursesmanager.educationservice.service.MarkService;
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
@RequestMapping("/marks")
@AllArgsConstructor
public class MarkController {

  private final MarkService markService;

  @PostMapping
  public ResponseEntity<Mark> create(@RequestBody Mark markEntity) {

    Mark createdMarkEntity = markService.save(markEntity);

    return ResponseEntity.ok(createdMarkEntity);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Mark> update(@PathVariable int id, @RequestBody Mark markEntity) {

    Mark updatedMarkEntity = markService.update(id, markEntity);

    return ResponseEntity.ok(updatedMarkEntity);
  }

  @GetMapping
  public ResponseEntity<List<Mark>> findAll() {

    return ResponseEntity.ok(markService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Mark> findById(@PathVariable int id) {

    return ResponseEntity.ok(markService.findById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> remove(@PathVariable int id) {

    markService.delete(id);
    return ResponseEntity.ok("Successfully deleted");
  }

}
