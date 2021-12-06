package md.ilie.coursesmanager.educationservice.repository;

import md.ilie.coursesmanager.educationservice.entity.LessonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends CrudRepository<LessonEntity, Integer> {
}
