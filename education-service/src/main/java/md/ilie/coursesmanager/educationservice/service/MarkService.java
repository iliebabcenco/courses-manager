package md.ilie.coursesmanager.educationservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Comment;
import md.ilie.coursesmanager.educationservice.entity.Mark;
import md.ilie.coursesmanager.educationservice.repository.MarkRepository;
import md.ilie.coursesmanager.educationservice.util.mongo.SequenceGeneratorService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MarkService {

  private final MarkRepository repository;
  private final SequenceGeneratorService sequenceGeneratorService;

  public List<Mark> findAllLessonsByIds(Integer... ids) {
    List<Mark> marksList = new ArrayList<>();
    repository.findAllById(Arrays.asList(ids)).forEach(marksList::add);
    return marksList;
  }

  public List<Mark> findAll() {
    List<Mark> marksList = new ArrayList<>();
    repository.findAll().forEach(marksList::add);
    return marksList;
  }

  public Mark findById(int id) {
    return repository.findById(id).orElseThrow(
        () -> new NoSuchElementException("Could not find mark: [" + id + "]"));
  }

  public Mark save(Mark markEntity) {

    markEntity.setId(sequenceGeneratorService.generateSequence(Comment.SEQUENCE_NAME));
    return repository.save(markEntity);
  }

  public void delete(int id) {

    repository.deleteById(id);
  }

  public Mark update(int id, Mark markEntity) {

    if (repository.existsById(id)) {
      repository.save(markEntity);
    }
    throw new NoSuchElementException("Could not find mark: [" + id + "]");
  }

}
