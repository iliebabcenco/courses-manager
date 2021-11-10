package md.ilie.coursesmanager.gateway.entity;

import lombok.Data;

import java.util.List;

@Data
public class User {

  private String uid;
  private String name;
  private String email;
  private String password;
  private String phoneNumber;
  private boolean isEmailVerified;
  private String picture;
//  private List<Role> role;

}
