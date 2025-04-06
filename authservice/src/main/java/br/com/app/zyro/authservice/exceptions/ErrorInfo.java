package br.com.app.zyro.authservice.exceptions;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ErrorInfo(
        HttpStatus httpStatus,
        ErrorCodeEnum errorCode,
        LocalDateTime dateTime,
        String traceId,
        String message
) {
}
