package md.ilie.coursesmanager.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import md.ilie.coursesmanager.educationservice.entity.Course;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Table(name = "students")
public class StudentEntity extends UserEntity{

//    @ManyToMany
//    private List<Course> studentCourses;

}
