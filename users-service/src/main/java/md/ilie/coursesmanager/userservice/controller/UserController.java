package md.ilie.coursesmanager.userservice.controller;

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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users-service")
@AllArgsConstructor
public class UserController {

  private UserService service;

  @PostMapping("/register")
  public ResponseEntity<UserEntityDto> registerUser(@RequestBody UserEntity userEntity) {
    try {
      return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(service.registerUser(userEntity));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while registering new user!", e);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserEntity> findById(@PathVariable("id") Integer id) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(service.findById(id));
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping
  public ResponseEntity<List<UserEntityDto>> getAllUsers() {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(service.getAllUsers());
  }

  @PreAuthorize("hasAuthority('ADMIN') || #id == authentication.principal.id")
  @PatchMapping("/{id}")
  public ResponseEntity<UserEntityDto> updateUser(@PathVariable Integer id, @RequestBody UserEntity userEntity) {

    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(service.updateUser(id, userEntity));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while registering new user!", e);
    }
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PatchMapping("/upgrade-roles/{id}")
  public ResponseEntity<?> updateUserRoles(@PathVariable("id") Integer id, @RequestBody List<RoleEnum> roles) {
    try {
      return ResponseEntity.ok(service.updateUserRoles(id, roles));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while registering new user!", e);
    }
  }

}
