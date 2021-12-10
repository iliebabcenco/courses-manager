package md.ilie.coursesmanager.educationservice;

import md.ilie.coursesmanager.educationservice.entity.CourseEntity;
import md.ilie.coursesmanager.educationservice.entity.MarkEntity;
import md.ilie.coursesmanager.educationservice.repository.CommentRepository;
import md.ilie.coursesmanager.educationservice.repository.CourseRepository;
import md.ilie.coursesmanager.educationservice.repository.LessonRepository;
import md.ilie.coursesmanager.educationservice.repository.MarkRepository;
import md.ilie.coursesmanager.educationservice.service.CourseService;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication(exclude = {
  DataSourceAutoConfiguration.class,
})
//@EntityScan(basePackages = "md.ilie.coursesmanager")
public class EducationServiceApplication implements CommandLineRunner {

    @Autowired
    private CourseRepository service;

    public static void main(String[] args) {
        SpringApplication.run(EducationServiceApplication.class, args);
    }


    @Override public void run(String... args) throws Exception {
        StudentEntity student = new StudentEntity();
        student.setUsername("ilui");
        service.save(CourseEntity.builder().id(333).description("igogog").endDate(LocalDate.now())
          .marks(List.of(new MarkEntity())).students(List.of(student))
          .build());
        System.out.println("\n\n\n saving....");
    }
}
