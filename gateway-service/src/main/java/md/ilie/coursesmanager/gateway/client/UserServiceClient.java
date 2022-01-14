package md.ilie.coursesmanager.gateway.client;

import java.util.List;
import md.ilie.coursesmanager.userservice.entity.RoleEnum;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "users-service", url = "localhost:8082")
public interface UserServiceClient {

  @PostMapping("/users-service/register")
  ResponseEntity<UserEntityDto> registerUser(@RequestBody UserEntity userEntity);

  @GetMapping("/users-service/{id}")
  ResponseEntity<UserEntity> findById(@PathVariable("id") Integer id);

  @PatchMapping("/users-service/upgrade-roles/{id}")
  ResponseEntity<UserEntityDto> updateUserRoles(@PathVariable("id") Integer id, @RequestBody List<RoleEnum> roles);

}
