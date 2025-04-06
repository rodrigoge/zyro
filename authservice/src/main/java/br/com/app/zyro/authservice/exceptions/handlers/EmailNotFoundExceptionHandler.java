package br.com.app.zyro.authservice.exceptions.handlers;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class EmailNotFoundExceptionHandler extends RuntimeException {

    private final HttpStatus httpStatus;
    private final LocalDateTime dateTime;
    private String message = "Email %s not found";

    public EmailNotFoundExceptionHandler(HttpStatus httpStatus, LocalDateTime dateTime, String email) {
        this.httpStatus = httpStatus;
        this.dateTime = dateTime;
        this.message = String.format(message, email);
    }
}
