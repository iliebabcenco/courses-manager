package md.ilie.coursesmanager.educationservice.config.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.ilie.coursesmanager.educationservice.service.UserService;
import md.ilie.coursesmanager.userservice.config.firebase.FirebaseTokenHolder;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;
import md.ilie.coursesmanager.userservice.config.firebase.FirebaseAuthenticationToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

@Component
@AllArgsConstructor
@Slf4j
public class FirebaseTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("Entering doFilterInternal from FirebaseTokenFilter");
        String authenticationHeader = request.getHeader("Authorization");
        Iterator<String> stringIterator = request.getHeaderNames().asIterator();
        System.out.println("\n\n\n BEFORE WHILE");
        while (stringIterator.hasNext()){
            System.out.println("header: "+stringIterator.next());
        }
        log.info("header "+authenticationHeader);
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
        Authentication auth = getAuthentication(decodedToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(FirebaseToken decodedToken) {
        assert decodedToken != null;
        return new FirebaseAuthenticationToken(decodedToken.getEmail(), new FirebaseTokenHolder(decodedToken));
    }

}
