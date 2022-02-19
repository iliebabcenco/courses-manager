package md.ilie.coursesmanager.gateway.graphql;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import lombok.Getter;
import md.ilie.coursesmanager.educationservice.entity.dto.response.UserResponseDto;
import md.ilie.coursesmanager.gateway.service.UserService;
import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UserDataLoaderComponent {

  @Autowired
  private UserService userService;
  private final BatchLoader<Integer, UserResponseDto> userBatchLoader;
  private final DataLoader<Integer, UserResponseDto> dataLoader;

  public UserDataLoaderComponent() {
    userBatchLoader =
        new BatchLoader<Integer, UserResponseDto>() {
          @Override public CompletionStage<List<UserResponseDto>> load(List<Integer> userIds) {
            return CompletableFuture.supplyAsync(() -> {
              return userService.getStudentsByIds(userIds);
            });
          }
        };
    dataLoader = DataLoaderFactory.newDataLoader(userBatchLoader);
  }

}
