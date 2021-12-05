package md.ilie.coursesmanager.userservice.repository;

import md.ilie.coursesmanager.userservice.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

  UserEntity findByUsername(String username);
  UserEntity findByEmail(String email);
}
