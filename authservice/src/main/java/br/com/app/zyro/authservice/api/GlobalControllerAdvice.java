package br.com.app.zyro.authservice.api;

import br.com.app.zyro.authservice.exceptions.ErrorCodeEnum;
import br.com.app.zyro.authservice.exceptions.ErrorInfo;
import br.com.app.zyro.authservice.exceptions.handlers.BadCredentialsExceptionHandler;
import br.com.app.zyro.authservice.exceptions.handlers.EmailNotFoundExceptionHandler;
import br.com.app.zyro.authservice.exceptions.handlers.GenericExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private ObjectMapper objectMapper;

    private String generateTraceId() {
        return UUID.randomUUID().toString();
    }

    @ExceptionHandler(GenericExceptionHandler.class)
    public ResponseEntity<ErrorInfo> handleGenericExceptionHandler(GenericExceptionHandler exceptionHandler) {
        return ResponseEntity
                .status(exceptionHandler.getHttpStatus())
                .body(ErrorInfo.builder()
                        .httpStatus(exceptionHandler.getHttpStatus())
                        .dateTime(exceptionHandler.getDateTime())
                        .errorCode(ErrorCodeEnum.INTERNAL_SERVER_ERROR)
                        .traceId(generateTraceId())
                        .message(exceptionHandler.getMessage())
                        .build());
    }

    @ExceptionHandler(BadCredentialsExceptionHandler.class)
    public ResponseEntity<ErrorInfo> handleBadCredentialsExceptionHandler(BadCredentialsExceptionHandler exceptionHandler) {
        return ResponseEntity
                .status(exceptionHandler.getHttpStatus())
                .body(ErrorInfo.builder()
                        .httpStatus(exceptionHandler.getHttpStatus())
                        .dateTime(exceptionHandler.getDateTime())
                        .errorCode(ErrorCodeEnum.BAD_CREDENTIALS)
                        .traceId(generateTraceId())
                        .message(exceptionHandler.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> handleBadCredentialsExceptionHandler(MethodArgumentNotValidException exceptionHandler) {
        var errors = exceptionHandler.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorInfo.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .dateTime(LocalDateTime.now())
                        .errorCode(ErrorCodeEnum.INVALID_REQUEST)
                        .traceId(generateTraceId())
                        .message(errors)
                        .build());
    }

    @ExceptionHandler(EmailNotFoundExceptionHandler.class)
    public ResponseEntity<ErrorInfo> handleEmailNotFoundExceptionHandler(EmailNotFoundExceptionHandler exceptionHandler) {
        return ResponseEntity
                .status(exceptionHandler.getHttpStatus())
                .body(ErrorInfo.builder()
                        .httpStatus(exceptionHandler.getHttpStatus())
                        .dateTime(exceptionHandler.getDateTime())
                        .errorCode(ErrorCodeEnum.BAD_CREDENTIALS)
                        .traceId(generateTraceId())
                        .message(exceptionHandler.getMessage())
                        .build());
    }
}
