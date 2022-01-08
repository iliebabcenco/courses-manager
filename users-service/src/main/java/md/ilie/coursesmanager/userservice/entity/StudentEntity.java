package md.ilie.coursesmanager.userservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "students")
public class StudentEntity extends UserEntity {

  public StudentEntity(UserEntity user) {
    this.id = user.id;
    this.email = user.email;
    this.phoneNumber = user.phoneNumber;
    this.picture = user.picture;
    this.username = user.username;
  }

}
