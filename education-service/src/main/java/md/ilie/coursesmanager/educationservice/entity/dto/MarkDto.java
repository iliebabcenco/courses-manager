package md.ilie.coursesmanager.educationservice.entity.dto;

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
public class MarkDto {

  private Integer id;
  private Integer value;
  private UserDto student;

}
