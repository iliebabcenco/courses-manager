package md.ilie.coursesmanager.userservice.service;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.userservice.entity.User;
import md.ilie.coursesmanager.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(int id) {
        return userRepository.findById(id).get();
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        userRepository.findAll().forEach(usersList::add);
        return usersList;
    }

    public User updateUser(int id, User user) throws Exception {
        if (userRepository.existsById(id)) {
            return userRepository.save(user);
        }
        throw new Exception();

    }



}
