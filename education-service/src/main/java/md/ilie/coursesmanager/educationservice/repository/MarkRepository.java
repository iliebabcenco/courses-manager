package md.ilie.coursesmanager.educationservice.repository;

import md.ilie.coursesmanager.educationservice.entity.MarkEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepository extends CrudRepository<MarkEntity, Integer> {
}
