package md.ilie.coursesmanager.educationservice.entity;

import lombok.*;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import md.ilie.coursesmanager.userservice.entity.TeacherEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
  private List<Comment> comments;
  private List<StudentEntity> students;
  private TeacherEntity teacher;
  private Course course;

}
