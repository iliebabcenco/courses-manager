package md.ilie.coursesmanager.educationservice.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

}
