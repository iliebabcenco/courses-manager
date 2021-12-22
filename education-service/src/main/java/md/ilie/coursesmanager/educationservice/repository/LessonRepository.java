package md.ilie.coursesmanager.educationservice.repository;

import md.ilie.coursesmanager.educationservice.entity.LessonEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;


public interface LessonRepository extends MongoRepository<LessonEntity, Integer> {

  @Query(value = "{ 'students': { $elemMatch: { 'id' : ?0 } }}")
  List<LessonEntity> findLessonsByTeacherIdOrStudentId(int userId);

}
