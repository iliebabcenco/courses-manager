package md.ilie.coursesmanager.userservice.service;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private UserRepository userRepository;

}
