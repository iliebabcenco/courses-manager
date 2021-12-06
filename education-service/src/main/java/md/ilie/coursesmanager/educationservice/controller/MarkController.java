package md.ilie.coursesmanager.educationservice.controller;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.MarkEntity;
import md.ilie.coursesmanager.educationservice.repository.MarkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class MarkController {

    private MarkRepository repository;

    public List<MarkEntity> findAllLessonsByIds(Integer... ids) {
        List<MarkEntity> marksList = new ArrayList<>();
        repository.findAllById(Arrays.asList(ids)).forEach(marksList::add);
        return marksList;
    }

    public List<MarkEntity> findAll() {
        List<MarkEntity> marksList = new ArrayList<>();
        repository.findAll().forEach(marksList::add);
        return marksList;
    }

    public MarkEntity save(MarkEntity markEntity) {

        return repository.save(markEntity);
    }

    public void delete(MarkEntity markEntity) {

        repository.delete(markEntity);
    }

}
