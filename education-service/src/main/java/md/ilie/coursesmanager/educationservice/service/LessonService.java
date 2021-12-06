package md.ilie.coursesmanager.educationservice.service;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.LessonEntity;
import md.ilie.coursesmanager.educationservice.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class LessonService {

    private LessonRepository repository;

    public List<LessonEntity> findAllLessonsByIds(Integer... ids) {
        List<LessonEntity> lessonsList = new ArrayList<>();
        repository.findAllById(Arrays.asList(ids)).forEach(lessonsList::add);
        return lessonsList;
    }

    public List<LessonEntity> findAll() {
        List<LessonEntity> lessonsList = new ArrayList<>();
        repository.findAll().forEach(lessonsList::add);
        return lessonsList;
    }

    public LessonEntity save(LessonEntity lessonEntity) {

        return repository.save(lessonEntity);
    }

    public void delete(LessonEntity lessonEntity) {

        repository.delete(lessonEntity);
    }
    
}
