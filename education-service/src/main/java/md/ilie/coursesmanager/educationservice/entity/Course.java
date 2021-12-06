package md.ilie.coursesmanager.educationservice.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import md.ilie.coursesmanager.userservice.entity.TeacherEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToMany
    private List<StudentEntity> students;
    @ManyToOne
    private TeacherEntity teacher;
    @OneToMany
    private List<Mark> marks;


}
