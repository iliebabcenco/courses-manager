package md.ilie.coursesmanager.gateway.config.firebase;

import com.google.firebase.auth.FirebaseToken;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.gateway.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
public class FirebaseAuthenticationProvider implements AuthenticationProvider {

  private UserService userService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    if (!supports(authentication.getClass())) {
      return null;
    }

    UserDetails details = userService.loadUserByUsername(authentication.getPrincipal()
      .toString());
    FirebaseToken token = (FirebaseToken) authentication.getCredentials();
    if (details == null) {

      details = userService.registerUser(token);
    }

    return new UsernamePasswordAuthenticationToken(details, token, details.getAuthorities());

  }

  @Override
  public boolean supports(Class<?> aClass) {
    return false;
  }
}
