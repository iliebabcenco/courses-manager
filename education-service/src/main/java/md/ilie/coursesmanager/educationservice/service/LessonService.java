package md.ilie.coursesmanager.educationservice.service;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.LessonEntity;
import md.ilie.coursesmanager.educationservice.repository.LessonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class LessonService {

    private final LessonRepository repository;

    public List<LessonEntity> findAllLessonsByIds(Integer... ids) {
        List<LessonEntity> lessonsList = new ArrayList<>();
        repository.findAllById(Arrays.asList(ids)).forEach(lessonsList::add);
        return lessonsList;
    }

    public List<LessonEntity> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    public LessonEntity findById(int id) {
        return repository.findById(id).orElseThrow(
          () -> new NoSuchElementException("Could not find lesson: [" + id + "]"));
    }

    public LessonEntity save(LessonEntity lessonEntity) {

        return repository.save(lessonEntity);
    }

    public void delete(int id) {

        repository.deleteById(id);
    }

    public LessonEntity update(int id, LessonEntity lessonEntity) {

        if (repository.existsById(id)) {
            repository.save(lessonEntity);
        }
        throw new NoSuchElementException("Could not find lesson: [" + id + "]");
    }

    public List<LessonEntity> findLessonsByUserId(int userId) {

        return repository.findLessonsByTeacherIdOrStudentId(userId);
    }

    public ResponseEntity<List<LessonEntity>> findLessonsByUserIdAndCourseId(int userId, int courseId) {

        return null;

    }
    
}
