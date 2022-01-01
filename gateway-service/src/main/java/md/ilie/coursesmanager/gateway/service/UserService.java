package md.ilie.coursesmanager.gateway.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.ilie.coursesmanager.gateway.client.TokenFeignInterceptor;
import md.ilie.coursesmanager.gateway.client.UserServiceClient;
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
  private final TokenFeignInterceptor tokenFeignInterceptor;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return null;
  }

  public UserEntity registerOrGetUser(UserEntity userEntity, String token) throws UsernameNotFoundException {
    tokenFeignInterceptor.setToken(token.substring(7));
    return usersClient.registerUser(userEntity).getBody();
  }


}
