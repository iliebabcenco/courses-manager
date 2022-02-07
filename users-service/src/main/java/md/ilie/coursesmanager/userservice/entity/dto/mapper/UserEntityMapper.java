package md.ilie.coursesmanager.userservice.entity.dto.mapper;

import md.ilie.coursesmanager.userservice.entity.RoleEnum;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

  @Mapping(target = "authorities", source = "userEntity", qualifiedByName = "mapAuthorities")
  UserEntityDto toUserEntityDto(UserEntity userEntity);

  List<UserEntityDto> toUserEntityDtos(List<UserEntity> userEntities);

  UserEntity toUserEntity(UserEntityRequest request);

  List<UserEntity> toUserEntities(List<UserEntityRequest> userEntityRequests);

  @Named("mapAuthorities")
  default List<RoleEnum> mapAuthorities(UserEntity userEntity) {

    return (List<RoleEnum>) userEntity.getAuthorities();
  }

}
