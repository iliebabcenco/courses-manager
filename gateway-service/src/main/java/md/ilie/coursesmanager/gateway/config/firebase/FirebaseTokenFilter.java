package md.ilie.coursesmanager.gateway.config.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.ilie.coursesmanager.userservice.config.firebase.FirebaseAuthenticationToken;
import md.ilie.coursesmanager.userservice.config.firebase.FirebaseTokenHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

@Component
@AllArgsConstructor
@Slf4j
public class FirebaseTokenFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    log.info("Entering doFilterInternal from FirebaseTokenFilter");
    String authenticationHeader = request.getHeader("Authorization");
    if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer "))
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing token!");
    FirebaseToken decodedToken;
    String token;
    try {
      token = authenticationHeader.substring(7);
      decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
    } catch (FirebaseAuthException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Error! " + e.getMessage());
    }
    if (decodedToken == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token!");
    }
    Authentication auth = getAuthentication(decodedToken);
    SecurityContextHolder.getContext().setAuthentication(auth);
    filterChain.doFilter(request, response);
  }

  private Authentication getAuthentication(FirebaseToken decodedToken) {
    assert decodedToken != null;
    return new FirebaseAuthenticationToken(decodedToken.getEmail(), new FirebaseTokenHolder(decodedToken));
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    AntPathMatcher pathMatcher = new AntPathMatcher();
    return pathMatcher.match("/users/register", request.getServletPath());
  }

}
