package md.ilie.coursesmanager.userservice.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntityRequest {

  private String email;
  private String password;
  private String username;
  private String picture;
  private String phoneNumber;

}
