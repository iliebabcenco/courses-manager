package md.ilie.coursesmanager.educationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import org.springframework.data.annotation.Id;
//import md.ilie.coursesmanager.userservice.entity.UserEntity;

//import javax.persistence.*;

//@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Table(name = "comments")
public class CommentEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
//    @ManyToOne
    private UserEntity user;

}
