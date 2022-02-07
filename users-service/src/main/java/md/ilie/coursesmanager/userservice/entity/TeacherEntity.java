package md.ilie.coursesmanager.userservice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder(toBuilder = true)
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
