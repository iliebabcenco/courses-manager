package md.ilie.coursesmanager.gateway.controller;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.gateway.client.UserServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthController {

    private UserServiceClient userServiceClient;

    @GetMapping("/check")
    public String checker() {
        System.out.println("CONTROLLER checker!!!");
        return "success-checked!";
    }

    @GetMapping("/users")
    public String getAllUsers() {
        userServiceClient.getAllUsers();
        return "RECEIVED ALL USERS!!!";
    }
}
