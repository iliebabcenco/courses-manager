package md.ilie.coursesmanager.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
//@AllArgsConstructor
@Getter
@Setter
@Table(name = "teachers")
public class TeacherEntity extends UserEntity{

//    @OneToMany
//    private List<Course> teacherCourses;

}
