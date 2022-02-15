package md.ilie.coursesmanager.gateway.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.ilie.coursesmanager.educationservice.entity.dto.response.UserLessonsResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.UserResponseDto;
import md.ilie.coursesmanager.gateway.service.UserService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserQueryResolver implements GraphQLQueryResolver {

  private UserService userService;

  public List<UserLessonsResponseDto> studentsByIdsWithLessons(List<Integer> ids) {

    log.info("Request students with ids: {}", ids);
    return toUseLessonsResponseDto(userService.getStudentsByIds(ids));
  }

  private List<UserLessonsResponseDto> toUseLessonsResponseDto(List<UserResponseDto> dtos) {
    List<UserLessonsResponseDto> list = new ArrayList<>();

    dtos.forEach(dto -> list.add(UserLessonsResponseDto.builder().student(dto).build()));

    return list;
  }

}
