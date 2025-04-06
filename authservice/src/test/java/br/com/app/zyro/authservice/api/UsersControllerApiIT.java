package br.com.app.zyro.authservice.api;

import br.com.app.zyro.authservice.db.RoleEnum;
import br.com.app.zyro.authservice.dtos.CreateUserRequestDTO;
import br.com.app.zyro.authservice.dtos.CreateUserResponseDTO;
import br.com.app.zyro.authservice.mocks.MockBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UsersControllerApiIT {

    private static final String PATH = "/v1/users";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void shouldCreateUserSuccessfully() {
        var request = MockBuilder.buildCreateUserRequestDTO();
        var response = testRestTemplate.postForEntity(PATH, request, CreateUserResponseDTO.class);
        var mockResponse = MockBuilder.buildCreateUserResponseDTO();
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(response.getBody().name()).isEqualTo(mockResponse.name());
        Assertions.assertThat(response.getBody().email()).isEqualTo(mockResponse.email());
        Assertions.assertThat(response.getBody().roleUser()).isEqualTo(RoleEnum.ADMIN);
        Assertions.assertThat(response.getBody().createdAt()).isNotBlank();
        Assertions.assertThat(response.getBody().updatedAt()).isNotBlank();
    }
}
