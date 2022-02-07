package md.ilie.coursesmanager.gateway.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.dto.response.CourseResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.LessonResponseDto;
import md.ilie.coursesmanager.gateway.service.EducationService;
import md.ilie.coursesmanager.gateway.service.UserService;
import md.ilie.coursesmanager.userservice.entity.RoleEnum;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

  private final UserService userService;
  private final EducationService courseService;

  @PostMapping("/register")
  public ResponseEntity<UserEntityDto> registerUser(@RequestBody UserEntityRequest userRequest) {
    try {
      return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(userService.registerOrGetUser(userRequest));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while registering new user!", e);
    }
  }

  @PostMapping("/register-admin")
  public ResponseEntity<UserEntityDto> registerAdminTest(@RequestBody UserEntityRequest userRequest) {
    try {
      return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(userService.registerAdmin(userRequest));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while registering new admin!", e);
    }
  }

  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  @PreAuthorize("hasAuthority('ADMIN')")
  @PatchMapping("/{id}/upgrade-roles")
  public ResponseEntity<UserEntityDto> updateUsersRoles(@PathVariable("id") Integer id,
                                                        @RequestBody List<RoleEnum> roles) {
    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(userService.updateUserRoles(id, roles));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Bad request for updating roles!", e);
    }
  }

  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  @PreAuthorize("#userId == authentication.principal.id")
  @GetMapping("/{id}/courses")
  public ResponseEntity<List<CourseResponseDto>> getUserCourses(@PathVariable("id") int userId) {
    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(courseService.getCoursesByUserId(userId));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error getting courses for such user", e);
    }
  }

  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  @PreAuthorize("#userId == authentication.principal.id")
  @GetMapping("/{id}/lessons")
  public ResponseEntity<List<LessonResponseDto>> getUserLessons(@PathVariable("id") int userId) {
    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(courseService.getLessonsByUserId(userId));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error getting lessons for such user", e);
    }
  }

  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  @GetMapping("/{id}")
  public ResponseEntity<UserEntityDto> findUserById(@PathVariable("id") int userId) {
    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(userService.findUserById(userId));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error getting user by id", e);
    }
  }

}
