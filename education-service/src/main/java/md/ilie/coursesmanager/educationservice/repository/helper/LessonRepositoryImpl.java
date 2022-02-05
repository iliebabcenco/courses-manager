package md.ilie.coursesmanager.educationservice.repository.helper;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Lesson;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@AllArgsConstructor
public class LessonRepositoryImpl implements LessonRepositoryCustom {

  private final MongoTemplate mongoTemplate;

  @Override
  public List<Lesson> getUserLessons(Integer userId) {
    final Query query = new Query();

    query.addCriteria(Criteria.where("students._id").is(userId));

    return mongoTemplate.find(query, Lesson.class);
  }
}
