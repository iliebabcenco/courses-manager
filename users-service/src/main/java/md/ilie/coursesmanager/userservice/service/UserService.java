package md.ilie.coursesmanager.userservice.service;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import md.ilie.coursesmanager.userservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity getUser(Integer id) {
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository.findByEmail(email);
    }
}
