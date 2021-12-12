package md.ilie.coursesmanager.educationservice;

import md.ilie.coursesmanager.educationservice.util.InsertTestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EducationServiceApplication implements CommandLineRunner {

    @Autowired
    InsertTestData insertTestData;

    public static void main(String[] args) {
        SpringApplication.run(EducationServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        insertTestData.insertTestData();
    }

}
