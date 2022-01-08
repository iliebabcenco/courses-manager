package md.ilie.coursesmanager.educationservice.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import md.ilie.coursesmanager.userservice.entity.TeacherEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "courses")
public class Course {

  @Id
  private Integer id;
  private String name;
  private String description;
  private LocalDate startDate;
  private LocalDate endDate;
  private List<StudentEntity> students = new ArrayList<>();
  private TeacherEntity teacher;
  private List<Mark> marks;

  private List<Lesson> lessons;
  private List<Comment> comments;

}
