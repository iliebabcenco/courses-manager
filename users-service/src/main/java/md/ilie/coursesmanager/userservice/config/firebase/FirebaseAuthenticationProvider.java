package md.ilie.coursesmanager.userservice.config.firebase;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import md.ilie.coursesmanager.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Component
public class FirebaseAuthenticationProvider implements AuthenticationProvider {

  private final UserService userService;

  @SneakyThrows
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    if (!supports(authentication.getClass())) {
      return null;
    }
    FirebaseAuthenticationToken authenticationToken = (FirebaseAuthenticationToken) authentication;
    UserEntity details = userService.loadUserByEmail(authenticationToken.getName());
    FirebaseTokenHolder holder = (FirebaseTokenHolder) authentication.getCredentials();
    if (details == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User " + holder.getEmail() + " is not registered!");
    }

    authenticationToken = new FirebaseAuthenticationToken(details.getEmail(), details, details.getAuthorities());
    return authenticationToken;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (FirebaseAuthenticationToken.class.isAssignableFrom(authentication));
  }

}
