package md.ilie.coursesmanager.educationservice.entity.dto.request;

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
public class CourseRequestDto {

  private String name;
  private String description;
  private LocalDate startDate;
  private LocalDate endDate;
  private List<Integer> studentsIds;
  private Integer teacherId;

}
