package md.ilie.coursesmanager.userservice.controller;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.userservice.entity.RoleEnum;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users-service")
@AllArgsConstructor
public class UserController {

  private UserService service;

  @PostMapping("/register")
  public ResponseEntity<UserEntityDto> registerUser(@RequestBody UserEntityRequest userEntity) {
    try {
      return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(service.registerUser(userEntity));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while registering new user!", e);
    }
  }

  @PostMapping("/register-admin")
  public ResponseEntity<UserEntityDto> registerAdminTest(@RequestBody UserEntityRequest userEntity) {
    try {
      return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(service.registerAdmin(userEntity));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while registering new admin!", e);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserEntityDto> findById(@PathVariable("id") Integer id) {
    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(service.findById(id));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "No such user", e);
    }
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping
  public ResponseEntity<List<UserEntityDto>> getAllUsers() {
    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(service.getAllUsers());
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "No users found!", e);
    }
  }

  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @GetMapping("/users-by-id")
  public ResponseEntity<List<UserEntityDto>> getAllUsersByIds(@RequestParam(value = "userIds") List<Integer> ids) {
    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(service.getAllUsersByIds(ids));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "No users found!", e);
    }
  }

  @PreAuthorize("hasAuthority('ADMIN') || #id == authentication.principal.id")
  @PatchMapping("/{id}")
  public ResponseEntity<UserEntityDto> updateUser(@PathVariable Integer id, @RequestBody UserEntityRequest userEntity) {
    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(service.updateUser(id, userEntity));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while updating user!", e);
    }
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PatchMapping("/upgrade-roles/{id}")
  public ResponseEntity<?> updateUserRoles(@PathVariable("id") Integer id, @RequestBody List<RoleEnum> roles) {
    try {
      return ResponseEntity.ok(service.updateUserRoles(id, roles));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while updating roles!", e);
    }
  }

  @PostMapping("/all-users-exist")
  public ResponseEntity<Boolean> checkAllUsersExistById(@RequestBody List<Integer> ids) {
    try {
      return ResponseEntity.ok(service.allUsersExistById(ids));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while checking ids!", e);
    }
  }

}
