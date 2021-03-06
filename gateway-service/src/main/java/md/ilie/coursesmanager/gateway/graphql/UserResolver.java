package md.ilie.coursesmanager.gateway.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.ilie.coursesmanager.educationservice.entity.dto.response.LessonResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.UserLessonsResponseDto;
import md.ilie.coursesmanager.gateway.service.EducationService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserResolver implements GraphQLResolver<UserLessonsResponseDto> {

  private LessonDataLoaderComponent dataLoaderComponent;
  private EducationService educationService;

  public List<LessonResponseDto> lessons(UserLessonsResponseDto responseDto) {

    Integer studentId = responseDto.getStudent().getId();

    List<LessonResponseDto> lessonResponseDtoList = educationService.getLessonsByUserId(studentId);
    dataLoaderComponent.getDataLoader().load(studentId);
    dataLoaderComponent.getDataLoader().dispatchAndJoin();

    return lessonResponseDtoList;
  }

}
