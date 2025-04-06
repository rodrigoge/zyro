package br.com.app.zyro.authservice.dtos;

import br.com.app.zyro.authservice.db.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserResponseDTO(

        @NotBlank
        String name,

        @Email
        @NotBlank
        String email,

        @NotBlank
        RoleEnum roleUser
) {
}
