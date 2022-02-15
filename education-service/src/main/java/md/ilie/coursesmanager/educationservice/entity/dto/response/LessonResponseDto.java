package md.ilie.coursesmanager.educationservice.entity.dto.response;

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
public class LessonResponseDto {

  private Integer id;
  private String name;
  private String content;
  private List<CommentResponseDto> comments;
  private List<UserResponseDto> students;
  private String courseName;
  private Integer courseId;

}
