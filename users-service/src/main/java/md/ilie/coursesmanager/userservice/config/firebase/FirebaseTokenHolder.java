package md.ilie.coursesmanager.userservice.config.firebase;

import com.google.firebase.auth.FirebaseToken;

import java.util.Map;

public class FirebaseTokenHolder {
  private FirebaseToken token;

  public FirebaseTokenHolder(FirebaseToken token) {
    this.token = token;
  }

  public String getEmail() {
    return token.getEmail();
  }

  public String getIssuer() {
    return token.getIssuer();
  }

  public String getName() {
    return token.getName();
  }

  public String getUid() {
    return token.getUid();
  }

  public Map<String, Object> getClaims() {
    return token.getClaims();
  }

  public String getPicture() {
    return token.getPicture();
  }

  public FirebaseToken getToken() {
    return token;
  }

}
