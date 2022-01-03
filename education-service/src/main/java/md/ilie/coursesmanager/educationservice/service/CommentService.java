package md.ilie.coursesmanager.educationservice.service;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Comment;
import md.ilie.coursesmanager.educationservice.repository.CommentRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CommentService {

  private final CommentRepository repository;

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
