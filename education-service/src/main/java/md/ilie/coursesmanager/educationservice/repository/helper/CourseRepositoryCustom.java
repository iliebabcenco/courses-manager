package md.ilie.coursesmanager.educationservice.repository.helper;

import java.util.List;
import md.ilie.coursesmanager.educationservice.entity.Course;

public interface CourseRepositoryCustom {

  List<Course> getUserCourses(Integer userId);

}
