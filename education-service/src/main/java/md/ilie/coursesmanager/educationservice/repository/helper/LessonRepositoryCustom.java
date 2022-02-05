package md.ilie.coursesmanager.educationservice.repository.helper;

import md.ilie.coursesmanager.educationservice.entity.Lesson;

import java.util.List;

public interface LessonRepositoryCustom {

  List<Lesson> getUserLessons(Integer userId);

}
