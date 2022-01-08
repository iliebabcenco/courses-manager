package md.ilie.coursesmanager.userservice.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.apache.catalina.User;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "teachers")
public class TeacherEntity extends UserEntity {

  public TeacherEntity(UserEntity user) {
    this.id = user.id;
    this.email = user.email;
    this.phoneNumber = user.phoneNumber;
    this.picture = user.picture;
    this.username = user.username;
  }

}
