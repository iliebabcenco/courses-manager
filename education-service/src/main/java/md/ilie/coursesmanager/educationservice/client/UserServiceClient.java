package md.ilie.coursesmanager.educationservice.client;

import feign.Headers;
import md.ilie.coursesmanager.userservice.config.firebase.FirebaseAuthenticationToken;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "users-service", url = "localhost:8083")
public interface UserServiceClient {

  @PostMapping("/users/register")
  @Headers("Content-Type: application/json")
  ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user);

  @GetMapping("/users?email={email}")
  ResponseEntity<UserDetails> loadUserByEmail(@PathVariable("email") String username);

  @GetMapping("/users/{username}")
  ResponseEntity<UserDetails> loadUserByUsername(@PathVariable("username") String username);

  @GetMapping("/users/auth")
  ResponseEntity<FirebaseAuthenticationToken> loadAuthByToken(@RequestHeader("Authorization") String token);

}
