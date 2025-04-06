package br.com.app.zyro.authservice.exceptions.handlers;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class GenericExceptionHandler extends RuntimeException {

    private final HttpStatus httpStatus;
    private final LocalDateTime dateTime;
    private final String message;

    public GenericExceptionHandler(HttpStatus httpStatus,
                                   LocalDateTime dateTime,
                                   String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.dateTime = dateTime;
        this.message = message;
    }
}
