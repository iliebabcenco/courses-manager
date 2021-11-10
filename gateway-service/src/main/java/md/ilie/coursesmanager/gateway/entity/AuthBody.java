package md.ilie.coursesmanager.gateway.entity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuthBody {

    private String idToken;
    private String email;
    private String refreshToken;
    private String expiresIn;
    private String localId;
    private boolean registered;

}
