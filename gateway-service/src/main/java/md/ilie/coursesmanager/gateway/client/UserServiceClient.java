package md.ilie.coursesmanager.gateway.client;

import java.util.List;
import md.ilie.coursesmanager.userservice.entity.RoleEnum;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "users-service", url = "localhost:8082")
public interface UserServiceClient {

  @PostMapping("/users-service/register")
  ResponseEntity<UserEntityDto> registerUser(@RequestBody UserEntityRequest userEntity);

  @PostMapping("/users-service/register-admin")
  ResponseEntity<UserEntityDto> registerAdminTest(@RequestBody UserEntityRequest userEntity);

  @GetMapping("/users-service/{id}")
  ResponseEntity<UserEntityDto> findById(@PathVariable("id") Integer id);

  @GetMapping("/users-service")
  ResponseEntity<List<UserEntityDto>> findAll();

  @PatchMapping("/users-service/upgrade-roles/{id}")
  ResponseEntity<UserEntityDto> updateUserRoles(@PathVariable("id") Integer id, @RequestBody List<RoleEnum> roles);

  @PostMapping("/users-service/all-users-exist")
  ResponseEntity<Boolean> checkAllUsersExistById(@RequestBody List<Integer> ids);

  @GetMapping("/users-service/users-by-id")
  ResponseEntity<List<UserEntityDto>> getAllUsersByIds(@RequestParam(value = "userIds") List<Integer> userIds);
}
