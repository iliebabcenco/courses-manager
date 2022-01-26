package md.ilie.coursesmanager.educationservice.service;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.Comment;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.Mark;
import md.ilie.coursesmanager.educationservice.entity.dto.CourseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.mapper.EducationServiceMapper;
import md.ilie.coursesmanager.educationservice.repository.CourseRepository;
import md.ilie.coursesmanager.educationservice.util.mongo.SequenceGeneratorService;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import md.ilie.coursesmanager.userservice.entity.TeacherEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseService {

  private final CourseRepository repository;
  private final EducationServiceMapper mapper;
  private final SequenceGeneratorService sequenceGeneratorService;

  public List<CourseDto> findAll() {

    List<Course> courseEntities = repository.findAll();

    return mapper.toCourseDtoList(courseEntities);
  }

  public CourseDto findById(int id) {

    Course course = repository.findById(id).orElseThrow(
        () -> new NoSuchElementException("Could not find course: [" + id + "]"));

    return mapper.toCourseDto(course);
  }

  public CourseDto save(Course courseEntity) {

    courseEntity.setId(sequenceGeneratorService.generateSequence(Comment.SEQUENCE_NAME));
    Course course = repository.save(courseEntity);

    return mapper.toCourseDto(course);
  }

  public void delete(int id) {

    repository.deleteById(id);
  }

  public CourseDto update(int id, Course courseEntity) {

    Course course;
    if (repository.existsById(id)) {
      course = repository.save(courseEntity);

      return mapper.toCourseDto(course);
    }

    throw new NoSuchElementException("Could not find course: [" + id + "]");
  }

  public List<CourseDto> getCoursesByUserId(Integer id) {

    List<Course> courseEntities = repository.getUserCourses(id);

    return mapper.toCourseDtoList(courseEntities);
  }

  public CourseDto setTeacherToCourse(Integer courseId, TeacherEntity teacher) {

    Course course = repository.findById(courseId).orElseThrow(
        () -> new NoSuchElementException("Could not find course: [" + courseId + "]"));
    course.setTeacher(teacher);

    return mapper.toCourseDto(repository.save(course));
  }

  public CourseDto addStudentToCourse(Integer courseId, StudentEntity student) {

    Course course = repository.findById(courseId).orElseThrow(
        () -> new NoSuchElementException("Could not find course: [" + courseId + "]"));
    course.addStudents(List.of(student));

    return mapper.toCourseDto(repository.save(course));

  }

  public CourseDto addCommentToCourse(Integer courseId, Comment comment) {

    Course course = repository.findById(courseId).orElseThrow(
        () -> new NoSuchElementException("Could not find course: [" + courseId + "]"));
    course.getComments().add(comment);

    return mapper.toCourseDto(repository.save(course));
  }

  public CourseDto addMarkToCourse(Integer courseId, Mark mark) {

    Course course = repository.findById(courseId).orElseThrow(
        () -> new NoSuchElementException("Could not find course: [" + courseId + "]"));
    course.addMarks(List.of(mark));

    return mapper.toCourseDto(repository.save(course));
  }
}
