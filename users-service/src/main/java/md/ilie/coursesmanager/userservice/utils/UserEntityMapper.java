package md.ilie.coursesmanager.userservice.utils;

import md.ilie.coursesmanager.userservice.config.firebase.FirebaseTokenHolder;
import md.ilie.coursesmanager.userservice.entity.RoleEnum;
import md.ilie.coursesmanager.userservice.entity.UserEntity;

import java.util.List;

public class UserEntityMapper {
  public static UserEntity firebaseTokenHolderToUserEntity(FirebaseTokenHolder holder, List<RoleEnum> roles) {

    return UserEntity
        .builder()
        .username(holder.getName())
        .email(holder.getEmail())
        .picture(holder.getPicture())
        .uid(holder.getUid())
        .authorities(roles)
        .build();
  }
}
