package md.ilie.coursesmanager.educationservice.repository;


import md.ilie.coursesmanager.educationservice.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, Integer> {
  
}
