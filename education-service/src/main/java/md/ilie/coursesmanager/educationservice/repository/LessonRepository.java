package md.ilie.coursesmanager.educationservice.repository;

import md.ilie.coursesmanager.educationservice.entity.LessonEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

//@Repository
public interface LessonRepository extends MongoRepository<LessonEntity, Integer> {
}
