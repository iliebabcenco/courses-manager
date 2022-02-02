package md.ilie.coursesmanager.educationservice.entity.dto.gatewayresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import md.ilie.coursesmanager.educationservice.entity.dto.request.CommentRequestDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentGatewayResponseDto {

  private CommentRequestDto commentRequestDto;
  private String username;

}
