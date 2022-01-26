package md.ilie.coursesmanager.educationservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.ilie.coursesmanager.educationservice.client.UserServiceClient;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
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

  public UserEntity registerOrGetUser(UserEntity userEntity) throws UsernameNotFoundException {
    return usersClient.registerUser(userEntity).getBody();
  }

}
