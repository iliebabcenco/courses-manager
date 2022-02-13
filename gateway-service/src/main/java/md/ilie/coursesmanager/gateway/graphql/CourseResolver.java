package md.ilie.coursesmanager.gateway.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.ilie.coursesmanager.educationservice.entity.dto.request.CommentRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.request.CourseRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.CourseResponseDto;
import md.ilie.coursesmanager.gateway.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

  public CourseResponseDto findById(int id) {
    try {
      return educationService.findById(id);
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Error while getting course by id!", e);
    }
  }

  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  public CourseResponseDto createCourse(CourseRequestDto courseEntity) {

    return educationService.create(courseEntity);
  }

  @PreAuthorize("#comment.userId == authentication.principal.id")
  public CourseResponseDto addCommentToCourse(Integer courseId, CommentRequestDto comment) {

    return educationService.addCommentToCourse(courseId, comment);
  }

}
