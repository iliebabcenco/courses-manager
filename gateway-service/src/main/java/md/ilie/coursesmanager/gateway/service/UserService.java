package md.ilie.coursesmanager.gateway.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.ilie.coursesmanager.gateway.client.UserServiceClient;
import md.ilie.coursesmanager.userservice.config.firebase.FirebaseAuthenticationToken;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    UserEntity user = usersClient.registerUser(userEntity).getBody();
//    assert user != null;
//    Authentication auth = new FirebaseAuthenticationToken(user.getEmail(), user, user.getAuthorities());
//    SecurityContextHolder.getContext().setAuthentication(auth);

    return user;
  }


}
