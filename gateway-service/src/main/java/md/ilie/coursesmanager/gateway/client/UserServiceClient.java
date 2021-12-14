package md.ilie.coursesmanager.gateway.client;

import md.ilie.coursesmanager.userservice.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "users-service", url = "localhost:8082", primary = false)
public interface UserServiceClient {

  @PostMapping("/users/register")
  ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity userEntity);

}
