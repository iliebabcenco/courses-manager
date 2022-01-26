package md.ilie.coursesmanager.educationservice.entity;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import md.ilie.coursesmanager.userservice.entity.TeacherEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "courses")
public class Course implements MongoEntity {

  @Id
  private Integer id;
  private String name;
  private String description;
  private LocalDate startDate;
  private LocalDate endDate;
  private List<StudentEntity> students;
  private TeacherEntity teacher;
  private List<Mark> marks;
  private List<Lesson> lessons;
  private List<Comment> comments;

  public void addMarks(List<Mark> marksToAdd) {
    if (marks == null) {
      marks = new ArrayList<>();
    }
    marks.addAll(marksToAdd);
    marksToAdd.forEach(mark -> {
      mark.setCourseId(this.getId());
      mark.setCourseName(this.getName());
    });
  }

  public void addLessons(List<Lesson> lessonsToAdd) {
    if (lessons == null) {
      lessons = new ArrayList<>();
    }
    lessons.addAll(lessonsToAdd);
    lessonsToAdd.forEach(lesson -> {
      lesson.setCourseId(this.getId());
      lesson.setCourseName(this.getName());
    });
  }

}
