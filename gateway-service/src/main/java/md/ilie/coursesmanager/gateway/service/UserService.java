package md.ilie.coursesmanager.gateway.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.ilie.coursesmanager.educationservice.entity.dto.response.UserResponseDto;
import md.ilie.coursesmanager.gateway.client.UserServiceClient;
import md.ilie.coursesmanager.userservice.entity.RoleEnum;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

  private final UserServiceClient usersClient;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return null;
  }

  public UserEntityDto registerOrGetUser(UserEntityRequest userEntity) throws UsernameNotFoundException {

    return usersClient.registerUser(userEntity).getBody();
  }

  public UserEntityDto registerAdmin(UserEntityRequest userEntity) throws UsernameNotFoundException {

    return usersClient.registerAdminTest(userEntity).getBody();
  }


  public UserEntityDto updateUserRoles(Integer id, List<RoleEnum> roles) {

    return usersClient.updateUserRoles(id, roles).getBody();
  }

  public UserEntityDto findUserById(int userId) {

    return usersClient.findById(userId).getBody();
  }

  public List<UserResponseDto> getStudentsByIds(List<Integer> ids) {

    return toUserResponseDtos(Objects.requireNonNull(usersClient.getAllUsersByIds(ids).getBody()));
  }

  private List<UserResponseDto> toUserResponseDtos(List<UserEntityDto> userEntityDtos) {
    List<UserResponseDto> list = new ArrayList<>();

    userEntityDtos.forEach(dto -> list.add(UserResponseDto.builder()
        .id(dto.getId())
        .username(dto.getUsername())
        .email(dto.getEmail())
        .phoneNumber(dto.getPhoneNumber())
        .build())
    );

    return list;


  }

}
