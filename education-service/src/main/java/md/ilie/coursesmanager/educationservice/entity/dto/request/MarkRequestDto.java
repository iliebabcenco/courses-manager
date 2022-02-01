package md.ilie.coursesmanager.educationservice.entity.dto.request;

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
public class MarkRequestDto {

  private Integer value;
  private Integer studentId;

}
