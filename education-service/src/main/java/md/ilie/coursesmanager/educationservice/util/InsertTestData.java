package md.ilie.coursesmanager.educationservice.util;

import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import md.ilie.coursesmanager.educationservice.entity.dto.request.CommentRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.request.CourseRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.request.LessonRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.request.MarkRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.CourseResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.LessonResponseDto;
import md.ilie.coursesmanager.educationservice.service.CourseService;
import md.ilie.coursesmanager.educationservice.service.LessonService;
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
  private CourseService courseService;

  @Autowired
  private LessonService lessonService;

  @Value("${insertTestData}")
  boolean insertData;

  public void insertTestData() {
    if (courseService.findAll().isEmpty() && this.insertData) {
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

    CourseRequestDto course = CourseRequestDto
        .builder()
        .name("Algorithms and Data Structures Course")
        .description("Full description for Algorithms and Data Structures")
        .startDate(LocalDate.of(2022, 9, 1))
        .endDate(LocalDate.of(2023, 3, 31))
        .build();

    MarkRequestDto mark1 = createMark(student1, 2);
    MarkRequestDto mark2 = createMark(student2, 4);
    MarkRequestDto mark3 = createMark(student3, 3);
    MarkRequestDto mark4 = createMark(student1, 5);

    CourseResponseDto persistedCourseDto = courseService.save(course);
    courseService.addMarkToCourse(persistedCourseDto.getId(), mark1);
    courseService.addMarkToCourse(persistedCourseDto.getId(), mark2);
    courseService.addMarkToCourse(persistedCourseDto.getId(), mark3);
    courseService.addMarkToCourse(persistedCourseDto.getId(), mark4);

  }

  private void insertSecondCourse() {
    TeacherEntity teacher = createTeacher("5");
    StudentEntity student1 = createStudent("4");
    StudentEntity student2 = createStudent("7");
    StudentEntity student3 = createStudent("8");
    LessonRequestDto lesson1 = createLesson(11);
    CommentRequestDto comment1 = createComment(13, teacher);
    CommentRequestDto comment3 = createComment(15, student3);

    CourseRequestDto course = CourseRequestDto
        .builder()
        .name("Frontend Course")
        .description("Full description for Frontend Course")
        .startDate(LocalDate.of(2022, 9, 1))
        .endDate(LocalDate.of(2023, 5, 31))
        .build();

    MarkRequestDto mark1 = createMark(student1, 5);
    MarkRequestDto mark3 = createMark(student3, 7);

    CourseResponseDto persistedCourseDto = courseService.save(course);
    courseService.addMarkToCourse(persistedCourseDto.getId(), mark1);
    courseService.addMarkToCourse(persistedCourseDto.getId(), mark3);
    courseService.addCommentToCourse(persistedCourseDto.getId(), comment1);
    courseService.addCommentToCourse(persistedCourseDto.getId(), comment3);
    courseService.addLessonToCourse(persistedCourseDto.getId(), lesson1);
  }

  private void insertFirstCourse() {

    TeacherEntity teacher = createTeacher("4");
    StudentEntity student1 = createStudent("1");
    StudentEntity student2 = createStudent("2");
    StudentEntity student3 = createStudent("3");
    LessonRequestDto lesson1 = createLesson(11);
    LessonRequestDto lesson2 = createLesson(12);
    CommentRequestDto comment1 = createComment(13, teacher);
    CommentRequestDto comment2 = createComment(14, student2);
    CommentRequestDto comment3 = createComment(15, student3);

    CourseRequestDto course = CourseRequestDto
        .builder()
        .name("Java Course")
        .description("Full description for Java Course")
        .startDate(LocalDate.of(2022, 9, 1))
        .endDate(LocalDate.of(2023, 5, 31))
        .build();

    MarkRequestDto mark1 = createMark(student1, 8);
    MarkRequestDto mark2 = createMark(student2, 7);
    MarkRequestDto mark3 = createMark(student3, 10);

    CourseResponseDto persistedCourseDto = courseService.save(course);
    LessonResponseDto persistedLessonDto = lessonService.save(lesson1);
    courseService.addMarkToCourse(persistedCourseDto.getId(), mark1);
    courseService.addMarkToCourse(persistedCourseDto.getId(), mark2);
    courseService.addMarkToCourse(persistedCourseDto.getId(), mark3);
    courseService.addCommentToCourse(persistedCourseDto.getId(), comment1);
    courseService.addCommentToCourse(persistedCourseDto.getId(), comment2);
    courseService.addCommentToCourse(persistedCourseDto.getId(), comment3);
    courseService.addLessonToCourse(persistedCourseDto.getId(), lesson1);
    courseService.addLessonToCourse(persistedCourseDto.getId(), lesson2);
    lessonService.addCommentToLesson(persistedLessonDto.getId(), comment3);
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

  public MarkRequestDto createMark(StudentEntity student, Integer value) {

    return MarkRequestDto
        .builder()
        .studentId(student.getId())
        .value(value)
        .build();
  }

  public LessonRequestDto createLesson(Integer value) {

    return LessonRequestDto
        .builder()
        .name("Lesson " + value)
        .content("Full descriptino for lesson " + value)
        .build();
  }

  public CommentRequestDto createComment(Integer value, UserEntity user) {

    return CommentRequestDto
        .builder()
        .content("Content " + value)
        .userId(user.getId())
        .build();
  }


}
