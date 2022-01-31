package md.ilie.coursesmanager.userservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "students")
public class StudentEntity extends UserEntity {

  public StudentEntity(UserEntityDto user) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.phoneNumber = user.getPhoneNumber();
    this.picture = user.getPicture();
    this.username = user.getUsername();
  }

}
