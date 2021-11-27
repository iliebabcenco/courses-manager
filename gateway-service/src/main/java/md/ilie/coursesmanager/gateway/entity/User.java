package md.ilie.coursesmanager.gateway.entity;

import lombok.Getter;
import lombok.Setter;
import md.ilie.coursesmanager.gateway.entity.enums.Role;



@Getter
@Setter
public class User {


  private Integer id;

  private String username;
  private String email;
  private String password;
  private String phoneNumber;
  private boolean isEmailVerified;
  private String picture;
  private Role role;

}
