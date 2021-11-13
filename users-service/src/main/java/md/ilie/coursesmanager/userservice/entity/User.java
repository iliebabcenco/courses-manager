package md.ilie.coursesmanager.userservice.entity;

import lombok.Getter;
import lombok.Setter;
import md.ilie.coursesmanager.userservice.entity.enums.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;
  private String email;
  private String password;
  private String phoneNumber;
  private boolean isEmailVerified;
  private String picture;
  @Enumerated(EnumType.STRING)
  private Role role;

}
