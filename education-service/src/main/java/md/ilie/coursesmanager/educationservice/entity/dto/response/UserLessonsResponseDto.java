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
public class UserLessonsResponseDto {

  private UserResponseDto student;
  private List<LessonResponseDto> lessons;

}
