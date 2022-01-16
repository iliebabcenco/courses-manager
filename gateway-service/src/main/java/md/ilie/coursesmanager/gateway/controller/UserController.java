package md.ilie.coursesmanager.gateway.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.dto.CourseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.LessonDto;
import md.ilie.coursesmanager.gateway.service.EducationService;
import md.ilie.coursesmanager.gateway.service.UserService;
import md.ilie.coursesmanager.userservice.entity.RoleEnum;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

  private final UserService userService;
  private final EducationService educationService;

  @PostMapping("/register")
  public ResponseEntity<UserEntityDto> registerUser(@RequestBody UserEntityRequest userRequest) {

    return ResponseEntity.ok(userService.registerOrGetUser(userRequest));
  }

  @PostMapping("/register-admin")
  public ResponseEntity<UserEntityDto> registerAdminTest(@RequestBody UserEntityRequest userRequest) {

    return ResponseEntity.ok(userService.registerAdmin(userRequest));
  }

  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  @PreAuthorize("hasAuthority('ADMIN')")
  @PatchMapping("/{id}/upgrade-roles")
  public ResponseEntity<UserEntityDto> updateUsersRoles(@PathVariable("id") Integer id,
      @RequestBody List<RoleEnum> roles) {

    return ResponseEntity.ok(userService.updateUserRoles(id, roles));
  }

  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  @PreAuthorize("#userId == authentication.principal.id")
  @GetMapping("/{id}/courses")
  public ResponseEntity<List<CourseDto>> getUserCourses(@PathVariable("id") int userId) {

    return educationService.getCoursesByUserId(userId);
  }

  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  @PreAuthorize("#userId == authentication.principal.id")
  @GetMapping("/{id}/lessons")
  public ResponseEntity<List<LessonDto>> getUserLessons(@PathVariable("id") int userId) {

    return educationService.getLessonsByUserId(userId);
  }

  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  @GetMapping("/{id}")
  public ResponseEntity<UserEntityDto> findUserById(@PathVariable("id") int userId) {

    return ResponseEntity.ok(userService.findUserById(userId));
  }

}
