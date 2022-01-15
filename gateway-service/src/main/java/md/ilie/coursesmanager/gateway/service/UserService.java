package md.ilie.coursesmanager.gateway.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.ilie.coursesmanager.gateway.client.UserServiceClient;
import md.ilie.coursesmanager.userservice.entity.RoleEnum;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

  private final UserServiceClient usersClient;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return null;
  }

  public UserEntityDto registerOrGetUser(UserEntity userEntity) throws UsernameNotFoundException {

    return usersClient.registerUser(userEntity).getBody();
  }


  public UserEntityDto updateUserRoles(Integer id, List<RoleEnum> roles) {

    return usersClient.updateUserRoles(id, roles).getBody();
  }

  public UserEntityDto findUserById(int userId) {

    return usersClient.findById(userId).getBody();
  }

}
