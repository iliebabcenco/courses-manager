package md.ilie.coursesmanager.educationservice.repository;


import md.ilie.coursesmanager.educationservice.entity.CommentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

//@Repository
public interface CommentRepository extends MongoRepository<CommentEntity, Integer> {
}
