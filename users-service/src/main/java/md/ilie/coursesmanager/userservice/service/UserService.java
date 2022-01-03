package md.ilie.coursesmanager.userservice.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.ilie.coursesmanager.userservice.entity.RoleEnum;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;
import md.ilie.coursesmanager.userservice.entity.dto.mapper.UserEntityMapper;
import md.ilie.coursesmanager.userservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private static final RoleEnum USER_ROLE = RoleEnum.USER;
  private final UserEntityMapper mapper;

  public UserEntityDto registerUser(UserEntity userEntity) throws Exception {

    if (userRepository.findByEmail(userEntity.getEmail()) != null) {
      throw new Exception("User already exists!");
    }

    UserEntity persistedUser = userRepository.save(userEntity);
    createFirebaseUser(persistedUser);
    persistedUser.setAuthorities(List.of(USER_ROLE));
    return mapper.toUserEntityDto(persistedUser);
  }

  private void createFirebaseUser(UserEntity userEntity) {

    UserRecord.CreateRequest request = new UserRecord.CreateRequest()
        .setEmail(userEntity.getEmail())
        .setPassword(userEntity.getPassword())
        //        .setPhoneNumber(userEntity.getPhoneNumber())
        .setDisplayName(userEntity.getUsername())
        //        .setPhotoUrl(userEntity.getPicture())
        .setDisabled(false);

    Map<String, Object> claims = new HashMap<>();
    claims.put("roles", List.of(USER_ROLE.getAuthority()));
    claims.put("id", userEntity.getId());

    UserRecord userRecord;
    try {
      userRecord = FirebaseAuth.getInstance().createUser(request);
      userEntity.setUid(userRecord.getUid());
      FirebaseAuth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);
    } catch (FirebaseAuthException e) {
      e.printStackTrace();
    }

  }

  public UserEntity findById(Integer id) {

    return userRepository.findById(id).orElseThrow(
        () -> new UsernameNotFoundException("Could not find user: [" + id + "]"));
  }

  public List<UserEntity> getAllUsers() {
    List<UserEntity> usersList = new ArrayList<>();
    userRepository.findAll().forEach(usersList::add);

    return usersList;
  }

  public UserEntity updateUser(Integer id, UserEntity userEntity) throws Exception {
    if (userRepository.existsById(id)) {
      return userRepository.save(userEntity);
    }
    throw new Exception();

  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    return userRepository.findByUsername(username);
  }

  public UserEntity loadUserByEmail(String email) throws UsernameNotFoundException {

    return userRepository.findByEmail(email);
  }

}
