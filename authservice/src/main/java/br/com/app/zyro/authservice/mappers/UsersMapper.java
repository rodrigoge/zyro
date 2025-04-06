package br.com.app.zyro.authservice.mappers;

import br.com.app.zyro.authservice.db.Users;
import br.com.app.zyro.authservice.dtos.CreateUserRequestDTO;
import br.com.app.zyro.authservice.dtos.CreateUserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "locked", constant = "false")
    @Mapping(target = "roles", expression = "java(java.util.List.of(requestDTO.roleUser()))")
    @Mapping(target = "authorities", ignore = true)
    Users toEntity(CreateUserRequestDTO requestDTO);

    CreateUserResponseDTO toResponseDTO(Users users);
}
