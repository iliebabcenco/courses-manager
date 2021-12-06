package md.ilie.coursesmanager.educationservice.entity;

import lombok.*;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer value;
    @ManyToOne
    private Course courses;
    @ManyToOne
    private StudentEntity student;

}
