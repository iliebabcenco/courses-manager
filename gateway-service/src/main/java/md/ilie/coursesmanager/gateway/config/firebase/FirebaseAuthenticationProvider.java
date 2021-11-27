package md.ilie.coursesmanager.gateway.config.firebase;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FirebaseAuthenticationProvider implements AuthenticationProvider {

  private UserDetailsService userService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    if (!supports(authentication.getClass())) {
      return null;
    }

    FirebaseAuthenticationToken authenticationToken = (FirebaseAuthenticationToken) authentication;
    UserDetails details = userService.loadUserByUsername(authenticationToken.getName());
    if (details == null) {
      throw new AuthenticationCredentialsNotFoundException("User Not Found");
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
