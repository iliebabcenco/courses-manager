package md.ilie.coursesmanager.educationservice.service;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.CommentEntity;
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

    public List<CommentEntity> findAllCommentsByIds(Integer... ids) {
        List<CommentEntity> commentsList = new ArrayList<>();
        repository.findAllById(Arrays.asList(ids)).forEach(commentsList::add);
        return commentsList;
    }

    public List<CommentEntity> findAll() {
        List<CommentEntity> commentsList = new ArrayList<>();
        repository.findAll().forEach(commentsList::add);
        return commentsList;
    }

    public CommentEntity findById(int id) {
        return repository.findById(id).orElseThrow(
          () -> new NoSuchElementException("Could not find comment: [" + id + "]"));
    }

    public CommentEntity save(CommentEntity commentEntity) {

        return repository.save(commentEntity);
    }

    public CommentEntity update(int id, CommentEntity commentEntity) {

        if (repository.existsById(id)) {
            repository.save(commentEntity);
        }
        throw new NoSuchElementException("Could not find comment: [" + id + "]");
    }

    public void delete(int id) {

        repository.deleteById(id);
    }

}
