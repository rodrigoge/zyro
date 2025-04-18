package br.com.app.zyro.authservice.mappers;

import br.com.app.zyro.authservice.db.Users;
import br.com.app.zyro.authservice.dtos.CreateUserRequestDTO;
import br.com.app.zyro.authservice.dtos.CreateUserResponseDTO;
import br.com.app.zyro.authservice.utils.DateFormatterUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

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

    @Mapping(target = "createdAt", expression = "java(formatDate(users.getCreatedAt()))")
    @Mapping(target = "updatedAt", expression = "java(formatDate(users.getUpdatedAt()))")
    CreateUserResponseDTO toResponseDTO(Users users);

    default String formatDate(LocalDateTime dateTime) {
        return DateFormatterUtils.format(dateTime);
    }
}
