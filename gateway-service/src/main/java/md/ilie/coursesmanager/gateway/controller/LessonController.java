package md.ilie.coursesmanager.gateway.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.dto.request.CommentRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.LessonResponseDto;
import md.ilie.coursesmanager.gateway.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@RequestMapping("/lessons")
@RestController
public class LessonController {

  private final EducationService educationService;

  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @PatchMapping("/{lessonId}/student/{studentId}")
  public ResponseEntity<LessonResponseDto> addStudentToLesson(@PathVariable("lessonId") Integer lessonId,
      @PathVariable("studentId") Integer studentId) {
    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(educationService.addStudentToLesson(lessonId, studentId));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while adding student to lesson", e);
    }
  }

  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  @PatchMapping("/{lessonId}/comment")
  public ResponseEntity<LessonResponseDto> addCommentToLesson(@PathVariable("lessonId") Integer lessonId,
      @RequestBody CommentRequestDto comment) {
    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(educationService.addCommentToLesson(lessonId, comment));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while adding comment to lesson", e);
    }
  }

}
