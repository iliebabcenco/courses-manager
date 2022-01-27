package md.ilie.coursesmanager.educationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Document(collection = "marks")
public class Mark implements MongoEntity {

  @Id
  private Integer id;
  private Integer value;
  private Integer studentId;
  private String studentName;

}
