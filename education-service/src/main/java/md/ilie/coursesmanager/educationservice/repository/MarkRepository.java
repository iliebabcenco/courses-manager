package md.ilie.coursesmanager.educationservice.repository;

import md.ilie.coursesmanager.educationservice.entity.MarkEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

//@Repository
public interface MarkRepository extends MongoRepository<MarkEntity, Integer> {
}
