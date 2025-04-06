package br.com.app.zyro.authservice.dtos;

import br.com.app.zyro.authservice.db.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserRequestDTO(

        @NotBlank(message = "Name cannot be null or empty")
        String name,

        @NotBlank(message = "Password cannot be null or empty")
        @Size(min = 8, max = 16, message = "Password must be between 8 and 20 characters")
        String password,

        @Email(message = "Invalid e-mail format")
        @NotBlank(message = "E-mail cannot be null or empty")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "E-mail cannot valid")
        String email,

        @NotNull(message = "Role cannot be null or empty")
        RoleEnum roleUser
) {
}
