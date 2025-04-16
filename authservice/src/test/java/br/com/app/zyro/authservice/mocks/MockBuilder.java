package br.com.app.zyro.authservice.mocks;

import br.com.app.zyro.authservice.db.RoleEnum;
import br.com.app.zyro.authservice.db.Users;
import br.com.app.zyro.authservice.dtos.CreateUserRequestDTO;
import br.com.app.zyro.authservice.dtos.CreateUserResponseDTO;
import br.com.app.zyro.authservice.utils.DateFormatterUtils;

import java.time.LocalDateTime;
import java.util.UUID;

public class MockBuilder {

    public static CreateUserRequestDTO buildCreateUserRequestDTO() {
        return new CreateUserRequestDTO(
                "John Doe",
                "JohnDoe123",
                "john.doe@test.com",
                RoleEnum.ADMIN
        );
    }

    public static CreateUserResponseDTO buildCreateUserResponseDTO() {
        return new CreateUserResponseDTO(
                "John Doe",
                "john.doe@test.com",
                RoleEnum.ADMIN,
                DateFormatterUtils.format(LocalDateTime.now()),
                DateFormatterUtils.format(LocalDateTime.now())
        );
    }

    public static Users buildUser() {
        var userEntity = new Users();
        userEntity.setUserId(generateUUID());
        userEntity.setName("John Doe");
        userEntity.setPassword("JohnDoe123");
        userEntity.setEmail("john.doe@test.com");
        userEntity.setRoleUser(RoleEnum.ADMIN);
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity.setEnabled(true);
        userEntity.setLocked(false);
        return userEntity;
    }

    public static UUID generateUUID() {
        return UUID.randomUUID();
    }
}
