package br.com.app.zyro.authservice.utils;

import br.com.app.zyro.authservice.exceptions.handlers.GenericExceptionHandler;
import br.com.app.zyro.authservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DateFormatterUtilsTest {

    @InjectMocks
    private DateFormatterUtils dateFormatterUtils;

    @Test
    void shouldFormatDateSuccessfully() {
        var dateTime = LocalDateTime.of(2025, 1, 1, 1, 0);
        var expected = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        var response = DateFormatterUtils.format(dateTime);
        Assertions.assertThat(response).isEqualTo(expected);
    }

    @Test
    void shouldThrowException_WhenDateTimeIsNull() {
        Assertions.assertThatThrownBy(() -> DateFormatterUtils.format(null))
                .isInstanceOf(GenericExceptionHandler.class);
    }
}
