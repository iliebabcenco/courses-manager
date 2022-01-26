package md.ilie.coursesmanager.educationservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Comment;
import md.ilie.coursesmanager.educationservice.repository.CommentRepository;
import md.ilie.coursesmanager.educationservice.util.mongo.SequenceGeneratorService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

  private final CommentRepository repository;
  private final SequenceGeneratorService sequenceGeneratorService;

  public List<Comment> findAllCommentsByIds(Integer... ids) {
    List<Comment> commentsList = new ArrayList<>();
    repository.findAllById(Arrays.asList(ids)).forEach(commentsList::add);
    return commentsList;
  }

  public List<Comment> findAll() {
    List<Comment> commentsList = new ArrayList<>();
    repository.findAll().forEach(commentsList::add);
    return commentsList;
  }

  public Comment findById(int id) {
    return repository.findById(id).orElseThrow(
        () -> new NoSuchElementException("Could not find comment: [" + id + "]"));
  }

  public Comment save(Comment commentEntity) {

    commentEntity.setId(sequenceGeneratorService.generateSequence(Comment.SEQUENCE_NAME));
    return repository.save(commentEntity);
  }

  public Comment update(int id, Comment commentEntity) {

    if (repository.existsById(id)) {
      repository.save(commentEntity);
    }

    throw new NoSuchElementException("Could not find comment: [" + id + "]");
  }

  public void delete(int id) {

    repository.deleteById(id);
  }

}
