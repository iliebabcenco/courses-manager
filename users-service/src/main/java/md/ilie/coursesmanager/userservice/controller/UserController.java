package md.ilie.coursesmanager.userservice.controller;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import md.ilie.coursesmanager.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable() int id) {
        return ResponseEntity.ok(service.getUser(id));
//        return ResponseEntity.ok("Got iit");
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {

        return ResponseEntity.ok(service.getAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        return ResponseEntity.ok(service.createUser(userEntity));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable int id, @RequestBody UserEntity userEntity) {
        try {
            return ResponseEntity.ok(service.updateUser(id, userEntity));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
