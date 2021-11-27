package md.ilie.coursesmanager.userservice.config.firebase;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.userservice.entity.RoleEntity;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import md.ilie.coursesmanager.userservice.service.UserService;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.List;

@AllArgsConstructor
@Component
public class FirebaseAuthenticationProvider implements AuthenticationProvider {

  private UserService userService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    System.out.println("\n\n\nFirebaseAuthenticationProvider\n\n\n");
    if (!supports(authentication.getClass())) {
      return null;
    }
    FirebaseAuthenticationToken authenticationToken = (FirebaseAuthenticationToken) authentication;
    System.out.println("\n\n\nUsername: "+authenticationToken.getName());
    System.out.println("\n\n\nPassword: "+authenticationToken.getCredentials());
    UserDetails details = userService.loadUserByUsername(authenticationToken.getName());
    if (details == null) {
      details = userService.createUser(new UserEntity(authenticationToken.getName(),
        authenticationToken.getCredentials().toString(), List.of(new RoleEntity("USER"))));//to do
    }

    authenticationToken = new FirebaseAuthenticationToken(details, authentication.getCredentials(),
      details.getAuthorities());

    return authenticationToken;
  }

  @Override
  public boolean supports(Class<?> authentication) {

    return (FirebaseAuthenticationToken.class.isAssignableFrom(authentication));
  }

}
