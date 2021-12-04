package md.ilie.coursesmanager.userservice.config.firebase;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.userservice.entity.RoleEntity;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import md.ilie.coursesmanager.userservice.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class FirebaseAuthenticationProvider implements AuthenticationProvider {

  private UserService userService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    if (!supports(authentication.getClass())) {
      return null;
    }
    FirebaseAuthenticationToken authenticationToken = (FirebaseAuthenticationToken) authentication;
    UserDetails details = userService.loadUserByUsername(authenticationToken.getName());
    if (details == null) {
        FirebaseTokenHolder holder = (FirebaseTokenHolder) authentication.getCredentials();
        UserEntity user = new UserEntity();
        user.setUsername(holder.getName());
        user.setEmail(holder.getEmail());
        user.setPicture(holder.getPicture());
        user.setId(holder.getUid());
        user.setAuthorities(authenticationToken.getAuthorities().stream().map(role
                -> new RoleEntity(role.getAuthority())).collect(Collectors.toList()));
       details = userService.createUser(user);
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
