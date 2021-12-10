package md.ilie.coursesmanager.educationservice.repository;

import md.ilie.coursesmanager.educationservice.entity.CourseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

//@Repository
public interface CourseRepository extends MongoRepository<CourseEntity, Integer> {
}
