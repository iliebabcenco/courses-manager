package md.ilie.coursesmanager.gateway.config;

import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.gateway.config.firebase.FirebaseAuthenticationProvider;
import md.ilie.coursesmanager.gateway.config.firebase.FirebaseTokenFilter;
import md.ilie.coursesmanager.gateway.service.UserService;
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

  private static final String[] AUTH_WHITELIST = {
      // -- Swagger UI v2
      "/v2/api-docs",
      "/swagger-resources",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/swagger-ui.html",
      "/webjars/**",
      // -- Swagger UI v3 (OpenAPI)
      "/v3/api-docs/**",
      "/swagger-ui/**"
      // other public endpoints of your API may be appended to this array
  };

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
        //                .authenticationProvider(firebaseProvider)
        .authorizeRequests()
        .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
        .antMatchers(HttpMethod.POST, "/users/register").permitAll()
        .antMatchers(HttpMethod.POST, "/users/register-admin").permitAll()
        .antMatchers(AUTH_WHITELIST).permitAll()
        //      .antMatchers("/users/**").hasAuthority(RoleEnum.ADMIN.getAuthority())
        .anyRequest().authenticated();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}
