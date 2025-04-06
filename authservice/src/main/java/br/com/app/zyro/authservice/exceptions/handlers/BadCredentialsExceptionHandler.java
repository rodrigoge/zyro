package br.com.app.zyro.authservice.exceptions.handlers;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;

import java.time.LocalDateTime;

@Getter
public class BadCredentialsExceptionHandler extends BadCredentialsException {

    private final HttpStatus httpStatus;
    private final LocalDateTime dateTime;
    private final String message;

    public BadCredentialsExceptionHandler(HttpStatus httpStatus,
                                          LocalDateTime dateTime,
                                          String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.dateTime = dateTime;
        this.message = message;
    }
}
