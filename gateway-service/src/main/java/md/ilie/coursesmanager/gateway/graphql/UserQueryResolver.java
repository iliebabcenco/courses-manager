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
  private UserDataLoaderComponent userDataLoaderComponent;

  public List<UserLessonsResponseDto> studentsByIdsWithLessons(List<Integer> ids) {

    log.info("Request students with ids: {}", ids);

    userDataLoaderComponent.getDataLoader().loadMany(ids);
    userDataLoaderComponent.getDataLoader().dispatchAndJoin();

    return toUseLessonsResponseDto(userService.getStudentsByIds(ids));

  }


  //  public List<UserLessonsResponseDto> studentsByIdsWithLessons(List<Integer> ids)
  //      throws ExecutionException, InterruptedException {
  //
  //    log.info("Request students with ids: {}", ids);
  //
  //    BatchLoader<Integer, UserResponseDto> userBatchLoader = new BatchLoader<Integer, UserResponseDto>() {
  //      @Override
  //      public CompletionStage<List<UserResponseDto>> load(List<Integer> userIds) {
  //        return CompletableFuture.supplyAsync(() -> {
  //          return userService.getStudentsByIds(userIds);
  //        });
  //      }
  //    };
  //
  //    DataLoader<Integer, UserResponseDto> userLoader = DataLoaderFactory.newDataLoader(userBatchLoader);
  //
  //    userLoader.loadMany(ids);
  //
  //    return toUseLessonsResponseDto(userLoader.dispatch().get());
  //
  //  }

  private List<UserLessonsResponseDto> toUseLessonsResponseDto(List<UserResponseDto> dtos) {
    List<UserLessonsResponseDto> list = new ArrayList<>();

    dtos.forEach(dto -> list.add(UserLessonsResponseDto.builder().student(dto).build()));

    return list;
  }

}
