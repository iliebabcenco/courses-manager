package md.ilie.coursesmanager.educationservice.repository;

import md.ilie.coursesmanager.educationservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {
}
