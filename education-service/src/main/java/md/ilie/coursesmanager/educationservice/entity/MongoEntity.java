package md.ilie.coursesmanager.educationservice.entity;

import org.springframework.data.annotation.Transient;

public interface MongoEntity {

  @Transient
  String SEQUENCE_NAME = "sequence";

}
