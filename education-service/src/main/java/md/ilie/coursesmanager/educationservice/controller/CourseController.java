package md.ilie.coursesmanager.educationservice.controller;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.CourseEntity;
import md.ilie.coursesmanager.educationservice.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseController {

    private CourseRepository repository;

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

    public CourseEntity save(CourseEntity CourseEntity) {

        return repository.save(CourseEntity);
    }

    public void delete(CourseEntity CourseEntity) {

        repository.delete(CourseEntity);
    }
}
