package md.ilie.coursesmanager.educationservice.service;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.dto.CourseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.mapper.EducationServiceMapper;
import md.ilie.coursesmanager.educationservice.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseService {

  private final CourseRepository repository;
  private final EducationServiceMapper mapper;

  public List<CourseDto> findAll() {

    List<Course> courseEntities = repository.findAll();

    return mapper.toCourseDtoList(courseEntities);
  }

  public CourseDto findById(int id) {

    Course course = repository.findById(id).orElseThrow(
        () -> new NoSuchElementException("Could not find course: [" + id + "]"));

    return mapper.toCourseDto(course);
  }

  public CourseDto save(Course courseEntity) {

    Course course = repository.save(courseEntity);

    return mapper.toCourseDto(course);
  }

  public void delete(int id) {

    repository.deleteById(id);
  }

  public CourseDto update(int id, Course courseEntity) {

    Course course;
    if (repository.existsById(id)) {
      course = repository.save(courseEntity);
      return mapper.toCourseDto(course);
    }
    throw new NoSuchElementException("Could not find course: [" + id + "]");
  }

  public List<CourseDto> getCoursesByUserId(Integer id) {

    List<Course> courseEntities = repository.getUserCourses(id);

    return mapper.toCourseDtoList(courseEntities);
  }
}
