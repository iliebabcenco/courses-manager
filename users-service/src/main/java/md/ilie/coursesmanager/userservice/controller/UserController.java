package md.ilie.coursesmanager.userservice.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.userservice.entity.RoleEnum;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;
import md.ilie.coursesmanager.userservice.service.UserService;
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

@RestController
@RequestMapping("/users-service")
@AllArgsConstructor
public class UserController {

  private UserService service;

  @GetMapping("/{id}")
  public ResponseEntity<UserEntity> findById(@PathVariable("id") Integer id) {
    UserEntity user = service.findById(id);

    return ResponseEntity.ok(user);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping
  public ResponseEntity<List<UserEntity>> getAllUsers() {
    return ResponseEntity.ok(service.getAllUsers());
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody UserEntity userEntity) {
    try {
      return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(service.registerUser(userEntity));
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(e.getMessage());
    }
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PatchMapping("/{id}")
  public ResponseEntity<UserEntityDto> updateUser(@PathVariable Integer id, @RequestBody UserEntity userEntity) {
    try {
      return ResponseEntity.ok(service.updateUser(id, userEntity));
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PatchMapping("/upgrade-roles/{id}")
  public ResponseEntity<?> updateUserRoles(@PathVariable("id") Integer id,
      @RequestBody List<RoleEnum> roles) {
    try {
      return ResponseEntity.ok(service.updateUserRoles(id, roles));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(e.getMessage());
    }
  }

}
