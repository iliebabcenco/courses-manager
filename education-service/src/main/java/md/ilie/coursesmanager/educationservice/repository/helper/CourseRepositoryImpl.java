package md.ilie.coursesmanager.educationservice.repository.helper;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Course;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;


@AllArgsConstructor
public class CourseRepositoryImpl implements CourseRepositoryCustom {

  private final MongoTemplate mongoTemplate;

  @Override
  public List<Course> getUserCourses(Integer userId) {

    final Query query = new Query();

    query.addCriteria(
        Criteria.where("").orOperator(
            Criteria.where("students._id").is(userId),
            Criteria.where("teacher._id").is(userId)
        )
    );

    return mongoTemplate.find(query, Course.class);
  }

}
