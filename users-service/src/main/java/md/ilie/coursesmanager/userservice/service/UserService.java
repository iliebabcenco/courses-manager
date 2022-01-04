package md.ilie.coursesmanager.userservice.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.ilie.coursesmanager.userservice.entity.RoleEnum;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;
import md.ilie.coursesmanager.userservice.entity.dto.mapper.UserEntityMapper;
import md.ilie.coursesmanager.userservice.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
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
    UserRecord firebaseUser = createFirebaseRecordUser(userEntity);
    userEntity.setUid(firebaseUser.getUid());
    UserEntity persistedUser = userRepository.save(userEntity);
    persistedUser.setAuthorities(List.of(USER_ROLE));
    setDefaultClaims(persistedUser);

    return mapper.toUserEntityDto(persistedUser);
  }

  private UserRecord createFirebaseRecordUser(UserEntity userEntity) throws FirebaseAuthException {
    UserRecord.CreateRequest request = new UserRecord.CreateRequest()
        .setEmail(userEntity.getEmail())
        .setPassword(userEntity.getPassword())
        .setDisplayName(userEntity.getUsername())
        .setDisabled(false);

    return FirebaseAuth.getInstance().createUser(request);
  }

  private void setDefaultClaims(UserEntity user) throws FirebaseAuthException {
    Map<String, Object> claims = new HashMap<>();
    claims.put("roles",
        user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
    claims.put("id", user.getId());
    FirebaseAuth.getInstance().setCustomUserClaims(user.getUid(), claims);
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

  public UserEntityDto updateUserRoles(Integer id, List<RoleEnum> roles) throws FirebaseAuthException {

    UserEntity user = userRepository.findById(id).orElseThrow(
        () -> new UsernameNotFoundException("Could not find user: [" + id + "]"));
    ((List<RoleEnum>) user.getAuthorities()).addAll(roles);
    updateFirebaseUserRoles(user.getUid(), roles);

    return mapper.toUserEntityDto(user);
  }

  private void updateFirebaseUserRoles(String uid, List<RoleEnum> roles) throws FirebaseAuthException {

    Map<String, Object> claims = new HashMap<>();
    List<String> strRoles = roles.stream().map(RoleEnum::getAuthority).collect(Collectors.toList());
    claims.put("roles", strRoles);
    FirebaseAuth.getInstance().setCustomUserClaims(uid, claims);

  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    return userRepository.findByUsername(username);
  }

  public UserEntity loadUserByEmail(String email) throws UsernameNotFoundException {

    return userRepository.findByEmail(email);
  }

}
