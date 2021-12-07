package md.ilie.coursesmanager.userservice.config.firebase;

import com.google.firebase.auth.FirebaseAuth;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import md.ilie.coursesmanager.userservice.entity.RoleEnum;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import md.ilie.coursesmanager.userservice.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class FirebaseAuthenticationProvider implements AuthenticationProvider {

  private UserService userService;

  @SneakyThrows
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    if (!supports(authentication.getClass())) {
      return null;
    }
    FirebaseAuthenticationToken authenticationToken = (FirebaseAuthenticationToken) authentication;
    UserDetails details = userService.loadUserByUsername(authenticationToken.getName());
    FirebaseTokenHolder holder = (FirebaseTokenHolder) authentication.getCredentials();
    List<RoleEnum> roles = null;
    if (details == null) {
      roles = List.of(RoleEnum.USER);
      UserEntity user = UserEntity
        .builder()
        .username(holder.getName())
        .email(holder.getEmail())
        .picture(holder.getPicture())
        .uid(holder.getUid())
        .authorities(roles)
        .build();
      Map<String, Object> claims = new HashMap<>();
      claims.put("roles", List.of(RoleEnum.USER.getAuthority()));
      FirebaseAuth.getInstance().setCustomUserClaims(holder.getUid(), claims);

      details = userService.createUser(user);
    } else {
      roles = ((List<String>) holder.getClaims().get("roles"))
        .stream().map(RoleEnum::valueOf).collect(Collectors.toList());
    }

    authenticationToken = new FirebaseAuthenticationToken(details, authentication.getCredentials(),
      roles);
    return authenticationToken;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (FirebaseAuthenticationToken.class.isAssignableFrom(authentication));
  }

}
