package md.ilie.coursesmanager.userservice.entity.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import md.ilie.coursesmanager.userservice.entity.RoleEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntityDto {

  private Integer id;

  private String username;
  private String email;
  private String phoneNumber;
  private String picture;

  private List<RoleEnum> authorities;

}
