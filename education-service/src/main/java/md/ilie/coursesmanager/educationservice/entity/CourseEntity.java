package md.ilie.coursesmanager.educationservice.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import md.ilie.coursesmanager.userservice.entity.StudentEntity;
//import md.ilie.coursesmanager.userservice.entity.TeacherEntity;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import md.ilie.coursesmanager.userservice.entity.TeacherEntity;
import org.springframework.data.annotation.Id;

//import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

//@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
//@Table(name = "courses")
public class CourseEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
//    @ManyToMany
    private List<StudentEntity> students;
//    @ManyToOne
    private TeacherEntity teacher;
//    @OneToMany
    private List<MarkEntity> marks;


}
