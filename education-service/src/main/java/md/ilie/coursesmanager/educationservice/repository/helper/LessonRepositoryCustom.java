package md.ilie.coursesmanager.educationservice.repository.helper;

import java.util.List;
import md.ilie.coursesmanager.educationservice.entity.Lesson;

public interface LessonRepositoryCustom {

  List<Lesson> getUserLessons(Integer userId);

}
