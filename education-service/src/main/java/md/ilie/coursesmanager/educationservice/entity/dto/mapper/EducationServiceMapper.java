package md.ilie.coursesmanager.educationservice.entity.dto.mapper;

import java.util.List;
import md.ilie.coursesmanager.educationservice.entity.Comment;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.Lesson;
import md.ilie.coursesmanager.educationservice.entity.Mark;
import md.ilie.coursesmanager.educationservice.entity.dto.response.CommentResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.CourseResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.LessonResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.MarkResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.UserResponseDto;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EducationServiceMapper {

  UserResponseDto toUserDto(UserEntity user);

  CommentResponseDto toCommentDto(Comment comment);

  CourseResponseDto toCourseDto(Course course);

  LessonResponseDto toLessonDto(Lesson lesson);

  MarkResponseDto toMarkDto(Mark mark);

  List<CourseResponseDto> toCourseDtoList(List<Course> courseList);

  List<LessonResponseDto> toLessonDtoList(List<Lesson> lessonList);

}
