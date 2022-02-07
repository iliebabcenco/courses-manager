package md.ilie.coursesmanager.gateway.client;

import md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse.CommentGatewayResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse.CourseGatewayResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse.LessonGatewayResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse.MarkGatewayResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.CourseResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.LessonResponseDto;
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

import java.util.List;

@FeignClient(name = "education-service", url = "localhost:8083")
public interface EducationServiceClient {

  @PostMapping("/courses")
  ResponseEntity<CourseResponseDto> createCourse(@RequestBody CourseGatewayResponseDto courseEntity);

  @PatchMapping("/courses/{id}")
  ResponseEntity<CourseResponseDto> update(@PathVariable("id") int id,
                                           @RequestBody CourseGatewayResponseDto courseEntity);

  @GetMapping("/courses")
  ResponseEntity<List<CourseResponseDto>> findAllCourses();

  @GetMapping("/courses/{id}")
  ResponseEntity<CourseResponseDto> findCourseById(@PathVariable("id") int id);

  @DeleteMapping("/courses/{id}")
  ResponseEntity<String> remove(@PathVariable("id") int id);

  @GetMapping("/users/{id}/courses")
  ResponseEntity<List<CourseResponseDto>> getCoursesByUserId(@PathVariable("id") int id);

  @GetMapping("/users/{id}/lessons")
  ResponseEntity<List<LessonResponseDto>> getLessonsByUserId(@PathVariable("id") int id);

  @PatchMapping("/courses/{courseId}/teacher")
  ResponseEntity<CourseResponseDto> setTeacherToCourse(@PathVariable("courseId") Integer courseId,
                                                       @RequestBody TeacherEntity teacher);

  @PatchMapping("/courses/{courseId}/student")
  ResponseEntity<CourseResponseDto> addStudentToCourse(@PathVariable("courseId") Integer courseId,
                                                       @RequestBody StudentEntity student);

  @PatchMapping("/courses/{courseId}/comment")
  ResponseEntity<CourseResponseDto> addCommentToCourse(@PathVariable("courseId") Integer courseId,
                                                       @RequestBody CommentGatewayResponseDto comment);

  @PatchMapping("/courses/{courseId}/mark")
  ResponseEntity<CourseResponseDto> addMarkToCourse(@PathVariable("courseId") Integer courseId,
                                                    @RequestBody MarkGatewayResponseDto mark);

  @PatchMapping("/lessons/{lessonId}/student")
  ResponseEntity<LessonResponseDto> addStudentToLesson(@PathVariable("lessonId") Integer lessonId,
                                                       @RequestBody List<StudentEntity> students);

  @PatchMapping("/lessons/{lessonId}/comment")
  ResponseEntity<LessonResponseDto> addCommentToLesson(@PathVariable("lessonId") Integer lessonId,
                                                       @RequestBody CommentGatewayResponseDto comment);

  @PatchMapping("/courses/{courseId}/lesson")
  ResponseEntity<CourseResponseDto> addLessonToCourse(@PathVariable("courseId") Integer courseId,
                                                      @RequestBody LessonGatewayResponseDto lesson);

}
