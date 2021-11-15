package md.ilie.coursesmanager.gateway.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.swagger.v3.oas.annotations.tags.Tag;
import md.ilie.coursesmanager.gateway.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "users-service")
@FeignClient(name = "users-service", url = "localhost:8081")
public interface UserServiceClient {

    @GetMapping("/users/{id}")
    ResponseEntity<User> getUser(@Param("id") int id);

    @GetMapping("/users")
    ResponseEntity<List<User>> getAllUsers();

    @PostMapping("/users")
    @Headers("Content-Type: application/json")
    ResponseEntity<User> createUser();

    @PatchMapping("/users/{id}")
    @Headers("Content-Type: application/json")
    ResponseEntity<User> updateUser(@Param("id") int id);

}
