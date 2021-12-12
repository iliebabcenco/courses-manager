package md.ilie.coursesmanager.educationservice.entity;


import lombok.*;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import md.ilie.coursesmanager.userservice.entity.TeacherEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "courses")
public class CourseEntity {

    @Id
    private Integer id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<StudentEntity> students;
    private TeacherEntity teacher;
    private List<MarkEntity> marks;

    private List<LessonEntity> lessons;
    private List<CommentEntity> comments;


}
