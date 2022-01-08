package md.ilie.coursesmanager.educationservice.service;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Lesson;
import md.ilie.coursesmanager.educationservice.repository.LessonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class LessonService {

  private final LessonRepository repository;

  public List<Lesson> findAllLessonsByIds(Integer... ids) {
    List<Lesson> lessonsList = new ArrayList<>();
    repository.findAllById(Arrays.asList(ids)).forEach(lessonsList::add);
    return lessonsList;
  }

  public List<Lesson> findAll() {
    return new ArrayList<>(repository.findAll());
  }

  public Lesson findById(int id) {
    return repository.findById(id).orElseThrow(
        () -> new NoSuchElementException("Could not find lesson: [" + id + "]"));
  }

  public Lesson save(Lesson lessonEntity) {

    return repository.save(lessonEntity);
  }

  public void delete(int id) {

    repository.deleteById(id);
  }

  public Lesson update(int id, Lesson lessonEntity) {

    if (repository.existsById(id)) {
      repository.save(lessonEntity);
    }
    throw new NoSuchElementException("Could not find lesson: [" + id + "]");
  }

}
