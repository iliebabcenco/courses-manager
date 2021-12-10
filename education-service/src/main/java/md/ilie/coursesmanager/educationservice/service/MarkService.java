package md.ilie.coursesmanager.educationservice.service;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.MarkEntity;
import md.ilie.coursesmanager.educationservice.repository.MarkRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class MarkService {

    private final MarkRepository repository;

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

    public MarkEntity findById(int id) {
        return repository.findById(id).orElseThrow(
          () -> new NoSuchElementException("Could not find mark: [" + id + "]"));
    }

    public MarkEntity save(MarkEntity markEntity) {

        return repository.save(markEntity);
    }

    public void delete(int id) {

        repository.deleteById(id);
    }

    public MarkEntity update(int id, MarkEntity markEntity) {

        if (repository.existsById(id)) {
            repository.save(markEntity);
        }
        throw new NoSuchElementException("Could not find mark: [" + id + "]");
    }

}
