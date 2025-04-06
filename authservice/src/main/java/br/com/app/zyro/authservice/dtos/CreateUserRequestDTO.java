package br.com.app.zyro.authservice.dtos;

import br.com.app.zyro.authservice.db.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateUserRequestDTO(

        @NotBlank
        String name,

        @NotBlank
        String password,

        @Email
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid e-mail")
        String email,

        @NotBlank
        RoleEnum roleUser
) {
}
