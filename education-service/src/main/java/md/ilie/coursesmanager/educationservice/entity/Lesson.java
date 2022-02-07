package md.ilie.coursesmanager.educationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "lessons")
public class Lesson implements MongoEntity {

  @Id
  private Integer id;
  private String name;
  private String content;
  private List<Comment> comments;
  private List<StudentEntity> students;
  private Integer courseId;
  private String courseName;

  public void addComments(List<Comment> commentsToAdd) {
    if (comments == null) {
      comments = new ArrayList<>();
    }
    comments.addAll(commentsToAdd);
  }

  public void addStudents(List<StudentEntity> studentsToAdd) {
    if (students == null) {
      students = new ArrayList<>();
    }
    students.addAll(studentsToAdd);
  }

}
