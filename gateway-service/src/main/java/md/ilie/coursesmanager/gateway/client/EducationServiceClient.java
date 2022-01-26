package md.ilie.coursesmanager.gateway.client;

import java.util.List;
import md.ilie.coursesmanager.educationservice.entity.Comment;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.Mark;
import md.ilie.coursesmanager.educationservice.entity.dto.CourseDto;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import md.ilie.coursesmanager.userservice.entity.TeacherEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "education-service", url = "localhost:8083")
public interface EducationServiceClient {

  @PostMapping("/courses")
  ResponseEntity<CourseDto> createCourse(@RequestBody Course courseEntity);

  @PatchMapping("/courses/{id}")
  ResponseEntity<CourseDto> update(@PathVariable("id") int id, @RequestBody Course courseEntity);

  @GetMapping("/courses")
  ResponseEntity<List<CourseDto>> findAllCourses();

  @GetMapping("/courses/{id}")
  ResponseEntity<CourseDto> findCourseById(@PathVariable("id") int id);

  @DeleteMapping("/courses/{id}")
  ResponseEntity<String> remove(@PathVariable("id") int id);

  @GetMapping("/users/{id}/courses")
  ResponseEntity<List<CourseDto>> getCoursesByUserId(@PathVariable("id") int id);

  @PatchMapping("/courses/{courseId}/teacher")
  ResponseEntity<CourseDto> setTeacherToCourse(@PathVariable("courseId") Integer courseId,
      @RequestBody TeacherEntity teacher);

  @PatchMapping("/courses/{courseId}/student")
  ResponseEntity<CourseDto> addStudentToCourse(@PathVariable("courseId") Integer courseId,
      @RequestBody StudentEntity student);

  @PatchMapping("/courses/{courseId}/comment")
  ResponseEntity<CourseDto> addCommentToCourse(@PathVariable Integer courseId, @RequestBody Comment comment);

  @PatchMapping("/courses/{courseId}/mark")
  ResponseEntity<CourseDto> addMarkToCourse(@PathVariable Integer courseId, @RequestBody Mark mark);

}
