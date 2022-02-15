package md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import md.ilie.coursesmanager.educationservice.entity.dto.request.LessonRequestDto;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonGatewayResponseDto {

  private LessonRequestDto lessonRequestDto;
  private List<StudentEntity> studentEntities;

}
