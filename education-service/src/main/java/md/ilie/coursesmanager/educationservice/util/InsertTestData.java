package md.ilie.coursesmanager.educationservice.util;

import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import md.ilie.coursesmanager.educationservice.entity.Comment;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.Lesson;
import md.ilie.coursesmanager.educationservice.entity.Mark;
import md.ilie.coursesmanager.educationservice.service.CourseService;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import md.ilie.coursesmanager.userservice.entity.TeacherEntity;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InsertTestData {

  @Autowired
  private CourseService service;

  @Value("${insertTestData}")
  boolean insertData;

  public void insertTestData() {
    if (service.findAll().isEmpty() && this.insertData) {
      log.info("Inserting test data...");
      insertFirstCourse();
      insertSecondCourse();
      insertThirdCourse();
      log.info("Test data successfully inserted.");
    } else {
      log.info("DB has already test data or insertData is false. Abort inserting.");
    }
  }

  private void insertThirdCourse() {

    TeacherEntity teacher = createTeacher("9");
    StudentEntity student1 = createStudent("10");
    StudentEntity student2 = createStudent("11");
    StudentEntity student3 = createStudent("12");

    Course course = Course
        .builder()
        .id(3)
        .name("Algorithms and Data Structures Course")
        .description("Full description for Algorithms and Data Structures")
        .startDate(LocalDate.of(2022, 9, 1))
        .endDate(LocalDate.of(2023, 3, 31))
        .teacher(teacher)
        .students(List.of(student1, student2, student3))
        .build();

    Mark mark1 = createMark(course, student1, 2);
    Mark mark2 = createMark(course, student2, 4);
    Mark mark3 = createMark(course, student3, 3);
    Mark mark4 = createMark(course, student1, 5);
    course.setMarks(List.of(mark1, mark2, mark3, mark4));

    service.save(course);
  }

  private void insertSecondCourse() {
    TeacherEntity teacher = createTeacher("5");
    StudentEntity student1 = createStudent("4");
    StudentEntity student2 = createStudent("7");
    StudentEntity student3 = createStudent("8");
    Lesson lesson1 = createLesson(11);
    Comment comment1 = createComment(13, teacher);
    Comment comment3 = createComment(15, student3);

    Course course = Course
        .builder()
        .id(2)
        .name("Frontend Course")
        .description("Full description for Frontend Course")
        .startDate(LocalDate.of(2022, 9, 1))
        .endDate(LocalDate.of(2023, 5, 31))
        .teacher(teacher)
        .students(List.of(student1, student2, student3))
        .build();

    Mark mark1 = createMark(course, student1, 5);
    Mark mark3 = createMark(course, student3, 7);
    course.setMarks(List.of(mark1, mark3));
    course.setLessons(List.of(lesson1));
    course.setComments(List.of(comment1));
    lesson1.setComments(List.of(comment3));

    service.save(course);
  }

  private void insertFirstCourse() {

    TeacherEntity teacher = createTeacher("4");
    StudentEntity student1 = createStudent("1");
    StudentEntity student2 = createStudent("2");
    StudentEntity student3 = createStudent("3");
    Lesson lesson1 = createLesson(11);
    Lesson lesson2 = createLesson(12);
    Comment comment1 = createComment(13, teacher);
    Comment comment2 = createComment(14, student2);
    Comment comment3 = createComment(15, student3);

    Course course = Course
        .builder()
        .id(1)
        .name("Java Course")
        .description("Full description for Java Course")
        .startDate(LocalDate.of(2022, 9, 1))
        .endDate(LocalDate.of(2023, 5, 31))
        .teacher(teacher)
        .students(List.of(student1, student2, student3))
        .build();

    Mark mark1 = createMark(course, student1, 8);
    Mark mark2 = createMark(course, student2, 7);
    Mark mark3 = createMark(course, student3, 10);
    course.setMarks(List.of(mark1, mark2, mark3));
    course.setLessons(List.of(lesson1, lesson2));
    course.setComments(List.of(comment1, comment2));
    lesson1.setComments(List.of(comment3));

    service.save(course);
  }

  public TeacherEntity createTeacher(String param) {
    TeacherEntity teacher = new TeacherEntity();
    teacher.setEmail("teacher" + param + "@mail.com");
    teacher.setUsername("Teacher " + param);
    teacher.setId(Integer.parseInt(param));
    return teacher;
  }

  public StudentEntity createStudent(String param) {
    StudentEntity student = new StudentEntity();
    student.setEmail("student" + param + "@mail.com");
    student.setUsername("student " + param);
    student.setId(Integer.parseInt(param));
    return student;
  }

  public Mark createMark(Course course, StudentEntity student, Integer value) {

    return Mark
        .builder()
        .id(value)
        .student(student)
        //                .course(course)
        .value(value)
        .build();
  }

  public Lesson createLesson(Integer value) {

    return Lesson
        .builder()
        .id(value)
        .name("Lesson " + value)
        .content("Full descriptino for lesson " + value)
        .build();
  }

  public Comment createComment(Integer value, UserEntity user) {

    return Comment
        .builder()
        .id(value)
        .content("Content " + value)
        .user(user)
        .build();
  }


}
