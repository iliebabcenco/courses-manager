package md.ilie.coursesmanager.educationservice.controller;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.CourseEntity;
import md.ilie.coursesmanager.educationservice.service.CourseService;
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
@RequestMapping("/courses")
public class CourseController {

  private final CourseService courseService;

  @PostMapping
  public ResponseEntity<CourseEntity> create(@RequestBody CourseEntity courseEntity){

    return ResponseEntity.ok(courseService.save(courseEntity));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<CourseEntity> update(@PathVariable int id, @RequestBody CourseEntity courseEntity){

    return ResponseEntity.ok(courseService.update(id, courseEntity));
  }

  @GetMapping
  public ResponseEntity<List<CourseEntity>> findAll(){

    return ResponseEntity.ok(courseService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CourseEntity> findById(@PathVariable int id){

    return ResponseEntity.ok(courseService.findById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> remove(@PathVariable int id){

    courseService.delete(id);
    return ResponseEntity.ok("Successfully deleted");
  }

}
