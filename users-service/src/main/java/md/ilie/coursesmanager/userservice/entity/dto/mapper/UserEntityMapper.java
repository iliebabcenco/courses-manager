package md.ilie.coursesmanager.userservice.entity.dto.mapper;

import java.util.List;
import md.ilie.coursesmanager.userservice.entity.RoleEnum;
import md.ilie.coursesmanager.userservice.entity.UserEntity;
import md.ilie.coursesmanager.userservice.entity.dto.UserEntityDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

  @Mapping(target = "authorities", source = "userEntity", qualifiedByName = "mapAuthorities")
  UserEntityDto toUserEntityDto(UserEntity userEntity);

  @Named("mapAuthorities")
  default List<RoleEnum> mapAuthorities(UserEntity userEntity) {

    return (List<RoleEnum>) userEntity.getAuthorities();
  }

}
