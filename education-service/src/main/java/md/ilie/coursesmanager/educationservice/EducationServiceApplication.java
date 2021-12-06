package md.ilie.coursesmanager.educationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "md.ilie.coursesmanager")
public class EducationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationServiceApplication.class, args);
    }

}
