package md.ilie.coursesmanager.educationservice.service;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.CourseEntity;
import md.ilie.coursesmanager.educationservice.repository.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository repository;

    public List<CourseEntity> findAllCoursesByIds(Integer... ids) {
        List<CourseEntity> coursesList = new ArrayList<>();
        repository.findAllById(Arrays.asList(ids)).forEach(coursesList::add);
        return coursesList;
    }

    public List<CourseEntity> findAll() {
        List<CourseEntity> coursesList = new ArrayList<>();
        repository.findAll().forEach(coursesList::add);
        return coursesList;
    }

    public CourseEntity findById(int id) {
        return repository.findById(id).orElseThrow(
          () -> new NoSuchElementException("Could not find course: [" + id + "]"));
    }

    public CourseEntity save(CourseEntity courseEntity) {

        return repository.save(courseEntity);
    }

    public void delete(int id) {

        repository.deleteById(id);
    }

    public CourseEntity update(int id, CourseEntity courseEntity) {

        if (repository.existsById(id)) {
            repository.save(courseEntity);
        }
        throw new NoSuchElementException("Could not find course: [" + id + "]");
    }

  public List<CourseEntity> getCoursesByUserId(int id) {

      return repository.findCourseEntitiesByTeacherId(id);
  }
}
