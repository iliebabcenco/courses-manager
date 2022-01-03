package md.ilie.coursesmanager.educationservice.entity.dto;

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
public class LessonDto {

  private Integer id;
  private String name;
  private String content;
  private List<CommentDto> comments;
  private List<UserDto> students;
  private UserDto teacher;
  private String courseName;
  private Integer courseId;

}
