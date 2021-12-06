package md.ilie.coursesmanager.educationservice.repository;


import md.ilie.coursesmanager.educationservice.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Integer> {
}
