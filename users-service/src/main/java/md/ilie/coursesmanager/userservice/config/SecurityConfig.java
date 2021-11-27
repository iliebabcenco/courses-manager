package md.ilie.coursesmanager.userservice.config;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.userservice.config.firebase.FirebaseAuthenticationProvider;
import md.ilie.coursesmanager.userservice.config.firebase.FirebaseTokenFilter;
import md.ilie.coursesmanager.userservice.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@AllArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private UserService userService;
  private FirebaseTokenFilter firebaseTokenFilter;
  private FirebaseAuthenticationProvider firebaseProvider;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.userDetailsService(userService);
    auth.authenticationProvider(firebaseProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // Enable CORS and disable CSRF
    http = http.cors().and().csrf().disable();
    // Set session management to stateless
    http = http
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and();
    // Set unauthorized requests exception handler
    http = http
      .exceptionHandling()
      .authenticationEntryPoint(
        (request, response, ex) -> {
          response.sendError(
            HttpServletResponse.SC_UNAUTHORIZED,
            ex.getMessage()
          );
        }
      )
      .and();
    // Set permissions on endpoints
    http.authorizeRequests()
      // public endpoints
      .antMatchers("/users/**").permitAll()
//      .antMatchers(HttpMethod.GET, "/users/**").permitAll()
//      .antMatchers(HttpMethod.POST, "/users/**").permitAll()
      // private endpoints
      .anyRequest().authenticated();

    // Add token filter
    http.addFilterBefore(
      firebaseTokenFilter,
      UsernamePasswordAuthenticationFilter.class
    );
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}
