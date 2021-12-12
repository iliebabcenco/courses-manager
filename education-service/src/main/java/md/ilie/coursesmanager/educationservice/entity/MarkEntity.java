package md.ilie.coursesmanager.educationservice.entity;

import lombok.*;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Document(collection = "marks")
public class MarkEntity {

    @Id
    private Integer id;
    private Integer value;
    private StudentEntity student;

}
