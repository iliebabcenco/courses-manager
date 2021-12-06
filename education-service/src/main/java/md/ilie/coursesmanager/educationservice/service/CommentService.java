package md.ilie.coursesmanager.educationservice.service;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.CommentEntity;
import md.ilie.coursesmanager.educationservice.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private CommentRepository repository;

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

    public CommentEntity save(CommentEntity commentEntity) {

        return repository.save(commentEntity);
    }

    public void delete(CommentEntity commentEntity) {

        repository.delete(commentEntity);
    }

}
