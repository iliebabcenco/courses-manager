package md.ilie.coursesmanager.educationservice.repository;

import md.ilie.coursesmanager.educationservice.entity.CourseEntity;
import md.ilie.coursesmanager.userservice.entity.TeacherEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<CourseEntity, Integer> {

  List<CourseEntity> findCourseEntitiesByTeacherId(int id);

}
