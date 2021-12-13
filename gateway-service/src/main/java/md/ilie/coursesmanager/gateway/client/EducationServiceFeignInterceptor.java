package md.ilie.coursesmanager.gateway.client;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.connection.ConnectionAuthTokenProvider;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.SneakyThrows;
import md.ilie.coursesmanager.userservice.config.firebase.FirebaseAuthenticationToken;
import md.ilie.coursesmanager.userservice.config.firebase.FirebaseTokenHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class EducationServiceFeignInterceptor implements RequestInterceptor {
    @SneakyThrows
    @Override
    public void apply(RequestTemplate requestTemplate) {

        FirebaseAuthenticationToken authentication =
                (FirebaseAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        FirebaseTokenHolder holder = (FirebaseTokenHolder) authentication.getCredentials();
//        String token = FirebaseAuth.getInstance().createCustomToken(holder.getUid());
        UserRecord user = FirebaseAuth.getInstance().getUser(holder.getUid());
        user.getTenantId();
//        ConnectionAuthTokenProvider.GetTokenCallback
//        System.out.println("\n\n\n"+token);
//        requestTemplate.header("Authorization: Bearer " + token);
    }
}
