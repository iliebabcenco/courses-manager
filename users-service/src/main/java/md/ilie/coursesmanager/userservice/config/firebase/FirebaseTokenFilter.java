package md.ilie.coursesmanager.userservice.config.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
@AllArgsConstructor
public class FirebaseTokenFilter extends OncePerRequestFilter {

    private FirebaseAuthenticationProvider firebaseAuthenticationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authenticationHeader = request.getHeader("Authorization");

        if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer "))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing token!");

        FirebaseToken decodedToken = null;
        try {
            String token = authenticationHeader.substring(7);
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);

        } catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Error! " + e.getMessage());
        }

        if (decodedToken == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token!");
        }
        System.out.println("Filter with dekoded token " + decodedToken.getEmail());
        System.out.println("Filter with dekoded token " + decodedToken.getUid());

        Authentication auth = getAuthentication(decodedToken);
        SecurityContextHolder.getContext().setAuthentication(firebaseAuthenticationProvider.authenticate(auth));
        logger.debug("Successfully Authenticated");

        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(FirebaseToken decodedToken) {
        assert decodedToken != null;
        return new FirebaseAuthenticationToken(decodedToken.getUid(), new FirebaseTokenHolder(decodedToken));
    }

}
