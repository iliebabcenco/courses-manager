package md.ilie.coursesmanager.userservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "teachers")
public class TeacherEntity extends UserEntity{

}
