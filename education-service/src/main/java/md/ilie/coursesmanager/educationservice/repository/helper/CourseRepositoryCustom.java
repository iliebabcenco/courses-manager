package md.ilie.coursesmanager.educationservice.repository.helper;

import md.ilie.coursesmanager.educationservice.entity.Course;

import java.util.List;

public interface CourseRepositoryCustom {

  List<Course> getUserCourses(Integer userId);

}
