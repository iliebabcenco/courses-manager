package md.ilie.coursesmanager.educationservice.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponseDto {

  private Integer id;
  private String name;
  private String description;
  private LocalDate startDate;
  private LocalDate endDate;
  private List<UserResponseDto> students;
  private UserResponseDto teacher;
  private List<MarkResponseDto> marks;
  private List<LessonResponseDto> lessons;
  private List<CommentResponseDto> comments;

}
