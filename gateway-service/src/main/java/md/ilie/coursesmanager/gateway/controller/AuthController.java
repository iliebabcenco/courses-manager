package md.ilie.coursesmanager.gateway.controller;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.CourseEntity;
import md.ilie.coursesmanager.gateway.client.EducationServiceClient;
import md.ilie.coursesmanager.gateway.client.UserServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@AllArgsConstructor
@RestController
public class AuthController {

    private EducationServiceClient educationServiceClient;

    @GetMapping("/check")
    public String checker() {
        System.out.println("CONTROLLER checker!!!");
        return "success-checked!";
    }

    @GetMapping("/gateway/courses")
    public ResponseEntity<List<CourseEntity>> getAllCourses() {
        return educationServiceClient.findAllCourses();
    }
}
