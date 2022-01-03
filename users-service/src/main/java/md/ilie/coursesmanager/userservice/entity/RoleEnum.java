package md.ilie.coursesmanager.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@NoArgsConstructor
@AllArgsConstructor
public enum RoleEnum implements GrantedAuthority {
  ADMIN("ADMIN"),
  USER("USER"),
  MANAGER("MANAGER");

  private String authority;

  @Override
  public String getAuthority() {
    return this.authority;
  }
}
