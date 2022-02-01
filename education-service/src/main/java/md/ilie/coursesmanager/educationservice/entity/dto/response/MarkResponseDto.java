package md.ilie.coursesmanager.educationservice.entity.dto.response;

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
public class MarkResponseDto {

  private Integer id;
  private Integer value;
  private Integer studentId;
  private String studentName;

}
