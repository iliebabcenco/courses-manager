package md.ilie.coursesmanager.educationservice.repository;

import md.ilie.coursesmanager.educationservice.entity.Mark;
import org.springframework.data.mongodb.repository.MongoRepository;

//@Repository
public interface MarkRepository extends MongoRepository<Mark, Integer> {
}
