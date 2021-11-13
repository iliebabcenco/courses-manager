package md.ilie.coursesmanager.userservice.controller;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.userservice.entity.User;
import md.ilie.coursesmanager.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable() int id) {

        return ResponseEntity.ok(service.getUser(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(service.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        return ResponseEntity.ok(service.createUser(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        try {
            return ResponseEntity.ok(service.updateUser(id, user));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
