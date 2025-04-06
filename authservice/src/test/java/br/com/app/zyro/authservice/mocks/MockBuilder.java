package br.com.app.zyro.authservice.mocks;

import br.com.app.zyro.authservice.db.RoleEnum;
import br.com.app.zyro.authservice.dtos.CreateUserRequestDTO;
import br.com.app.zyro.authservice.dtos.CreateUserResponseDTO;
import br.com.app.zyro.authservice.utils.DateFormatterUtils;

import java.time.LocalDateTime;

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
}
