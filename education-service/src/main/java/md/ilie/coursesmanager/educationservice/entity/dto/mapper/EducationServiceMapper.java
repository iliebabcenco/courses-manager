package md.ilie.coursesmanager.educationservice.entity.dto.mapper;

import java.util.List;
import md.ilie.coursesmanager.educationservice.entity.Comment;
import md.ilie.coursesmanager.educationservice.entity.Course;
import md.ilie.coursesmanager.educationservice.entity.Lesson;
import md.ilie.coursesmanager.educationservice.entity.Mark;
import md.ilie.coursesmanager.educationservice.entity.User;
import md.ilie.coursesmanager.educationservice.entity.dto.request.CommentRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.request.CourseRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.request.LessonRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.request.MarkRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.request.UserRequestDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.CommentResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.CourseResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.LessonResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.MarkResponseDto;
import md.ilie.coursesmanager.educationservice.entity.dto.response.UserResponseDto;
import md.ilie.coursesmanager.userservice.entity.StudentEntity;
import md.ilie.coursesmanager.userservice.entity.TeacherEntity;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EducationServiceMapper {

  UserResponseDto toUserResponseDto(UserEntity user);

  User toUser(UserEntityDto userEntityDto);

  UserResponseDto teacherToUserResponseDto(TeacherEntity teacher);

  UserResponseDto studentToUserResponseDto(StudentEntity student);

  CommentResponseDto toCommentResponseDto(Comment comment);

  CourseResponseDto toCourseResponseDto(Course course);

  LessonResponseDto toLessonResponseDto(Lesson lesson);

  MarkResponseDto toMarkResponseDto(Mark mark);

  List<CourseResponseDto> toCourseResponseDtoList(List<Course> courseList);

  List<LessonResponseDto> toLessonResponseDtoList(List<Lesson> lessonList);

  Course toCourseEntity(CourseRequestDto courseRequestDto);

  List<Course> toCourseEntitiesList(List<CourseRequestDto> courseRequestDtos);

  Lesson toLessonEntity(LessonRequestDto lessonRequestDto);

  List<Lesson> toLessonEntitiesList(List<LessonRequestDto> lessonRequestDtos);

  Mark toMarkEntity(MarkRequestDto markRequestDto);

  List<Mark> toMarkEntitiesList(List<MarkRequestDto> markRequestDtos);

  Comment toCommentEntity(CommentRequestDto commentRequestDto);

  List<Comment> toCommentEntitiesList(List<CommentRequestDto> commentRequestDtos);

  TeacherEntity toTeacherEntity(UserRequestDto userRequestDto);

  StudentEntity toStudentEntity(UserRequestDto userRequestDto);
}
