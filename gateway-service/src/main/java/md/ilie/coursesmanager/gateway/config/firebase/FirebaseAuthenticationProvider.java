package md.ilie.coursesmanager.gateway.config.firebase;

import static md.ilie.coursesmanager.userservice.utils.UserEntityMapper.firebaseTokenHolderToUserEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import md.ilie.coursesmanager.userservice.config.firebase.FirebaseAuthenticationToken;
import md.ilie.coursesmanager.userservice.config.firebase.FirebaseTokenHolder;
import md.ilie.coursesmanager.userservice.entity.RoleEnum;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Component
public class FirebaseAuthenticationProvider implements AuthenticationProvider {

  @SneakyThrows
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    if (!supports(authentication.getClass())) {
      return null;
    }
    FirebaseAuthenticationToken authenticationToken;
    FirebaseTokenHolder holder = (FirebaseTokenHolder) authentication.getCredentials();
    List<RoleEnum> roles = ((List<String>) holder.getClaims().get("roles"))
        .stream().map(RoleEnum::valueOf).collect(Collectors.toList());
    UserEntity userDetails = firebaseTokenHolderToUserEntity(holder, roles);
    BigDecimal userId = ((BigDecimal) holder.getClaims().get("id"));

    if (userDetails == null || userId == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid user! please register as a new user.");
    }
    userDetails.setId(userId.intValue());
    authenticationToken = new FirebaseAuthenticationToken(userDetails, holder, roles);
    return authenticationToken;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (FirebaseAuthenticationToken.class.isAssignableFrom(authentication));
  }

}
