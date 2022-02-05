package md.ilie.coursesmanager.userservice.repository;

import md.ilie.coursesmanager.userservice.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

  UserEntity findByUsername(String username);

  UserEntity findByEmail(String email);

  Integer countUserEntitiesByIdIn(List<Integer> ids);
}
