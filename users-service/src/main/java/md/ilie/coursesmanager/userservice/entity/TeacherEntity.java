package md.ilie.coursesmanager.userservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "teachers")
public class TeacherEntity extends UserEntity {

  public TeacherEntity(UserEntityDto user) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.phoneNumber = user.getPhoneNumber();
    this.picture = user.getPicture();
    this.username = user.getUsername();
  }

}
