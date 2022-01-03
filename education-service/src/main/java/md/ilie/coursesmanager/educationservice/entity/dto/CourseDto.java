package md.ilie.coursesmanager.educationservice.entity.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {

  private Integer id;
  private String name;
  private String description;
  private LocalDate startDate;
  private LocalDate endDate;
  private List<UserDto> students;
  private UserDto teacher;
  private List<MarkDto> marks;

  private List<LessonDto> lessons;
  private List<CommentDto> comments;

}
