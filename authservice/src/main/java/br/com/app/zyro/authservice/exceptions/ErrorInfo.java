package br.com.app.zyro.authservice.exceptions;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ErrorInfo(
        String traceId,
        HttpStatus httpStatus,
        ErrorCodeEnum errorCode,
        String dateTime,
        String message
) {
}
