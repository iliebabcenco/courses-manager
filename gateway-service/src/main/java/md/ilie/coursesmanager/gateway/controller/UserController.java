package md.ilie.coursesmanager.gateway.controller;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.gateway.service.UserService;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<UserEntityDto> registerUser(@RequestBody UserEntity user) {

    return ResponseEntity.ok(userService.registerOrGetUser(user));
  }

}
