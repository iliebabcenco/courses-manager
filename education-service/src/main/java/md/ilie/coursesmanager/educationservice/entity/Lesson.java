package md.ilie.coursesmanager.educationservice.entity;

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
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "lessons")
public class Lesson {

  @Id
  private Integer id;
  private String name;
  private String content;
  @DocumentReference
  private List<Comment> comments;
  private List<StudentEntity> students;
  private TeacherEntity teacher;
  private Course course;

}
