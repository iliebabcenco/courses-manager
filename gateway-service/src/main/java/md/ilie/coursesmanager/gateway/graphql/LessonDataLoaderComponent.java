package md.ilie.coursesmanager.gateway.graphql;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import lombok.Getter;
import md.ilie.coursesmanager.educationservice.entity.dto.response.LessonResponseDto;
import md.ilie.coursesmanager.gateway.service.EducationService;
import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class LessonDataLoaderComponent {

  @Autowired
  private EducationService educationService;
  private final BatchLoader<Integer, LessonResponseDto> lessonsBatchLoader;
  private final DataLoader<Integer, LessonResponseDto> dataLoader;

  public LessonDataLoaderComponent() {
    lessonsBatchLoader =
        new BatchLoader<Integer, LessonResponseDto>() {
          @Override public CompletionStage<List<LessonResponseDto>> load(List<Integer> userIds) {
            return CompletableFuture.supplyAsync(() -> {
              return educationService.getLessonsByUserId(userIds.get(0));
            });
          }
        };
    dataLoader = DataLoaderFactory.newDataLoader(lessonsBatchLoader);
  }

}
