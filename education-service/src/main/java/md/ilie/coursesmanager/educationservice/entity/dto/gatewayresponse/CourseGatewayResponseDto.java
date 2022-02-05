package md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import md.ilie.coursesmanager.educationservice.entity.dto.request.CourseRequestDto;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import md.ilie.coursesmanager.userservice.entity.TeacherEntity;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseGatewayResponseDto {

  private CourseRequestDto requestDto;
  private List<StudentEntity> studentEntityList;
  private TeacherEntity teacherEntity;

}
