package md.ilie.coursesmanager.userservice.controller;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import md.ilie.coursesmanager.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

  private UserService service;

  @GetMapping("/{id}")
  public ResponseEntity<UserEntity> findById(@PathVariable() Integer id) {
    return ResponseEntity.ok(service.findById(id));
  }

  @GetMapping
  public ResponseEntity<List<UserEntity>> getAllUsers() {
    return ResponseEntity.ok(service.getAllUsers());
  }

  @PostMapping("/register")
  public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity userEntity) {
    try {
      return ResponseEntity.ok(service.registerUser(userEntity));
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PatchMapping("/{id}")
  public ResponseEntity<UserEntity> updateUser(@PathVariable Integer id, @RequestBody UserEntity userEntity) {
    try {
      return ResponseEntity.ok(service.updateUser(id, userEntity));
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PatchMapping("/upgrade/{id}")
  public ResponseEntity<UserEntity> upgradeToAdmin(@PathVariable Integer id, @RequestBody UserEntity userEntity) {
    try {
        return ResponseEntity.ok(service.updateUser(id, userEntity));
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{email}")
  public ResponseEntity<UserDetails> loadUserByEmail(@PathVariable String email) {
    try {
      return ResponseEntity.ok(service.loadUserByEmail(email));
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

}
