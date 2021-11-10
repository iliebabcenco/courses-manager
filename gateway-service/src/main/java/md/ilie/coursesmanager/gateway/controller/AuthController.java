package md.ilie.coursesmanager.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @GetMapping("check")
  public String checker() {
    return "success-checked!";
  }
}
