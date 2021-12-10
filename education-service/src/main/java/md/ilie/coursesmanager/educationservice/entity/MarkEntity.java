package md.ilie.coursesmanager.educationservice.entity;

import lombok.*;
//import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

//import javax.persistence.*;

//@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
//@Table(name = "marks")
public class MarkEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer value;
    @DBRef(db = "courses")
    private CourseEntity courses;
//    @ManyToOne
    private StudentEntity student;

}
