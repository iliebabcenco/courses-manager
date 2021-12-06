package md.ilie.coursesmanager.userservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity

@Getter
@Setter
@Table(name = "students")
public class StudentEntity extends UserEntity{

}
