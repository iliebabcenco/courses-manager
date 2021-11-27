package md.ilie.coursesmanager.userservice.utils;

import md.ilie.coursesmanager.userservice.config.firebase.FirebaseAuthenticationToken;
import md.ilie.coursesmanager.userservice.entity.UserEntity;

public class UserEntityMapper {
  public static UserEntity firebaseTokenToUserEntity(FirebaseAuthenticationToken firebaseToken){



    return UserEntity
      .builder()
      .email(firebaseToken.getName())
      .username("")
      .build();
  }
}
