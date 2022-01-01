package md.ilie.coursesmanager.gateway.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class TokenFeignInterceptor implements RequestInterceptor {

  private String token;

  @Override
  public void apply(RequestTemplate requestTemplate) {
    if (token != null && !token.isEmpty()) {
      requestTemplate.header("Authorization", "Bearer " + token);
    }
  }

  public void setToken(String token) {
    this.token = token;
  }
}
