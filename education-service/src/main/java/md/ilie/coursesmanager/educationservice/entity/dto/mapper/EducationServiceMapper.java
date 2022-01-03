package md.ilie.coursesmanager.educationservice.entity.dto.mapper;

import java.util.List;
import md.ilie.coursesmanager.educationservice.entity.Comment;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.Lesson;
import md.ilie.coursesmanager.educationservice.entity.Mark;
import md.ilie.coursesmanager.educationservice.entity.dto.CommentDto;
import md.ilie.coursesmanager.educationservice.entity.dto.CourseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.LessonDto;
import md.ilie.coursesmanager.educationservice.entity.dto.MarkDto;
import md.ilie.coursesmanager.educationservice.entity.dto.UserDto;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EducationServiceMapper {

  UserDto toUserDto(UserEntity user);

  CommentDto toCommentDto(Comment comment);

  CourseDto toCourseDto(Course course);

  @Mapping(target = "courseName", source = "lesson.course.name")
  @Mapping(target = "courseId", source = "lesson.course.id")
  LessonDto toLessonDto(Lesson lesson);

  MarkDto toMarkDto(Mark mark);

  List<CourseDto> toCourseDtoList(List<Course> courseList);

}
