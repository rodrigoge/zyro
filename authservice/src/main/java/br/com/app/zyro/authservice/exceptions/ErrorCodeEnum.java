package br.com.app.zyro.authservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    INVALID_REQUEST("ZYRO-001", "Invalid request, check fields and try again"),
    BAD_CREDENTIALS("ZYRO-002", "Invalid email or password"),
    INTERNAL_SERVER_ERROR("ZYRO-003", "An unexpected error occurred");

    private final String message;
    private final String defaultMessage;
}
