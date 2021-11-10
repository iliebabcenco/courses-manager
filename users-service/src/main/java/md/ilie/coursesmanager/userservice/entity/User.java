package md.ilie.coursesmanager.userservice.entity;

import md.ilie.coursesmanager.userservice.entity.enums.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
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
