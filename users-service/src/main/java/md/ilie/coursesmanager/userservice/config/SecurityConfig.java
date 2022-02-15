package md.ilie.coursesmanager.userservice.config;

import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.userservice.config.firebase.FirebaseAuthenticationProvider;
import md.ilie.coursesmanager.userservice.config.firebase.FirebaseTokenFilter;
import md.ilie.coursesmanager.userservice.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private UserService userService;
  private FirebaseTokenFilter firebaseTokenFilter;
  private FirebaseAuthenticationProvider firebaseProvider;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService);
    auth.authenticationProvider(firebaseProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http = http.cors().and().csrf().disable();
    http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
    http = http.exceptionHandling().authenticationEntryPoint(
        (request, response, ex) -> {
          response.sendError(
              HttpServletResponse.SC_BAD_REQUEST,
              ex.getMessage());
        }).and();

    http
        .addFilterBefore(firebaseTokenFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/users-service/register").permitAll()
        .antMatchers(HttpMethod.POST, "/users-service/register-admin").permitAll()
        //      .antMatchers("/users/register").permitAll()
        //        .antMatchers(HttpMethod.GET, "/users/**").hasAuthority(RoleEnum.USER.getAuthority())
        //        .antMatchers("/users/**").hasAuthority(RoleEnum.ADMIN.getAuthority())
        .anyRequest().authenticated();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}
