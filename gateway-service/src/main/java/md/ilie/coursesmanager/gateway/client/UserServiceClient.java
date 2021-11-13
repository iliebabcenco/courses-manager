package md.ilie.coursesmanager.gateway.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import md.ilie.coursesmanager.gateway.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient
public interface UserServiceClient {

    @RequestLine("GET /{id}")
    ResponseEntity<User> getUser(@Param("id") int id);

    @RequestLine("GET")
    ResponseEntity<List<User>> getAllUsers();

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    ResponseEntity<User> createUser();

    @RequestLine("PATCH")
    @Headers("Content-Type: application/json")
    ResponseEntity<User> updateUser(@Param("id") int id);

}
