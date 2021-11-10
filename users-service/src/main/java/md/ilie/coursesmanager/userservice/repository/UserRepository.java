package md.ilie.coursesmanager.userservice.repository;

import md.ilie.coursesmanager.userservice.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
