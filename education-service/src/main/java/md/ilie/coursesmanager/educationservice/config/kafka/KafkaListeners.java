package md.ilie.coursesmanager.educationservice.config.kafka;

import lombok.AllArgsConstructor;
import md.ilie.coursesmanager.educationservice.entity.dto.mapper.EducationServiceMapper;
import md.ilie.coursesmanager.educationservice.service.UserService;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaListeners {

  private final UserService userService;
  private final EducationServiceMapper mapper;

  @KafkaListener(topics = "userServiceTopic", groupId = "groupId", containerFactory = "factory")
  void userServiceListener(UserEntityDto user) {
    userService.save(mapper.toUser(user));
  }

}
