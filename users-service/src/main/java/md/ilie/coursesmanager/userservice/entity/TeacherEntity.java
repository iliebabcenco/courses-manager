package md.ilie.coursesmanager.userservice.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "teachers")
public class TeacherEntity extends UserEntity {

}
