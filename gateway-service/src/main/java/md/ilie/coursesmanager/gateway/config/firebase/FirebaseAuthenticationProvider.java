package md.ilie.coursesmanager.gateway.config.firebase;

import static md.ilie.coursesmanager.userservice.utils.UserEntityMapper.firebaseTokenHolderToUserEntity;

import com.google.firebase.auth.FirebaseAuth;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import md.ilie.coursesmanager.gateway.client.EducationServiceFeignInterceptor;
import md.ilie.coursesmanager.gateway.service.UserService;
import md.ilie.coursesmanager.userservice.config.firebase.FirebaseAuthenticationToken;
import md.ilie.coursesmanager.userservice.config.firebase.FirebaseTokenHolder;
import md.ilie.coursesmanager.userservice.entity.RoleEnum;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    FirebaseTokenHolder holder = (FirebaseTokenHolder) authentication.getCredentials();
    List<RoleEnum> roles = ((List<String>) holder.getClaims().get("roles"))
      .stream().map(RoleEnum::valueOf).collect(Collectors.toList());
    if (roles.isEmpty()) {
      roles = List.of(RoleEnum.USER);
      Map<String, Object> claims = new HashMap<>();
      claims.put("roles", List.of(RoleEnum.USER.getAuthority()));
      FirebaseAuth.getInstance().setCustomUserClaims(holder.getUid(), claims);
    }
    UserEntity user = firebaseTokenHolderToUserEntity(holder, roles);
    UserDetails details = userService.registerOrGetUser(user);
    if (details == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid user!");
    }
    authenticationToken = new FirebaseAuthenticationToken(details, holder,
      roles);
    return authenticationToken;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (FirebaseAuthenticationToken.class.isAssignableFrom(authentication));
  }

}
