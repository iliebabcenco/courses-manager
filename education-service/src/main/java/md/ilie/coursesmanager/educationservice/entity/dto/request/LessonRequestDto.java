package md.ilie.coursesmanager.educationservice.entity.dto.request;

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
public class LessonRequestDto {

  private String name;
  private String content;
  private List<Integer> studentsIds;
  private Integer teacherId;

}
