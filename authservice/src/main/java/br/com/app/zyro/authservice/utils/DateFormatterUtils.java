package br.com.app.zyro.authservice.utils;

import br.com.app.zyro.authservice.exceptions.handlers.GenericExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateFormatterUtils {

    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(LocalDateTime dateTime) {
        if (dateTime == null) {
            throw new GenericExceptionHandler(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    DateFormatterUtils.format(LocalDateTime.now()),
                    "Error on format date"
            );
        }
        var formatter = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);
        return dateTime.format(formatter);
    }
}
