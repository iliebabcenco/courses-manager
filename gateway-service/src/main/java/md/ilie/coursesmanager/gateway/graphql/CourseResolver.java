package md.ilie.coursesmanager.gateway.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.ilie.coursesmanager.educationservice.entity.dto.response.CourseResponseDto;
import md.ilie.coursesmanager.gateway.service.EducationService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Slf4j
@RequiredArgsConstructor
@Controller
public class CourseResolver implements GraphQLQueryResolver, GraphQLMutationResolver, GraphQLSubscriptionResolver {

  private final EducationService educationService;

  public List<CourseResponseDto> courses() {
    log.info("Get all courses request in CourseResolver");
    return educationService.findAll();
  }

  public String getIt() {
    log.info("Get all courses request in CourseResolver");
    return "GOt it!";
  }

}
