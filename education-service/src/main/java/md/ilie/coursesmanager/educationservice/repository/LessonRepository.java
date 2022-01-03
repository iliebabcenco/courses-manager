package md.ilie.coursesmanager.educationservice.repository;

import md.ilie.coursesmanager.educationservice.entity.Lesson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;


public interface LessonRepository extends MongoRepository<Lesson, Integer> {

  @Query(value = "{ 'students': { $elemMatch: { 'id' : ?0 } }}")
  List<Lesson> findLessonsByTeacherIdOrStudentId(int userId);

}
