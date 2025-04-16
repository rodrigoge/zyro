package br.com.app.zyro.authservice.services;

import br.com.app.zyro.authservice.db.RoleEnum;
import br.com.app.zyro.authservice.db.UsersRepository;
import br.com.app.zyro.authservice.dtos.CreateUserRequestDTO;
import br.com.app.zyro.authservice.exceptions.handlers.BadCredentialsExceptionHandler;
import br.com.app.zyro.authservice.exceptions.handlers.EmailNotFoundExceptionHandler;
import br.com.app.zyro.authservice.exceptions.handlers.GenericExceptionHandler;
import br.com.app.zyro.authservice.mappers.UsersMapper;
import br.com.app.zyro.authservice.mocks.MockBuilder;
import br.com.app.zyro.authservice.utils.EncryptUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UsersServiceTest {

    @InjectMocks
    private UsersService usersService;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private UsersMapper usersMapper;

    @Mock
    private EncryptUtils encryptUtils;

    @Test
    void shouldCreateUserSuccessfully() {
        var user = MockBuilder.buildUser();
        var request = MockBuilder.buildCreateUserRequestDTO();
        var response = MockBuilder.buildCreateUserResponseDTO();
        Mockito.when(usersRepository.findByEmail(request.email())).thenReturn(Optional.empty());
        Mockito.when(usersMapper.toEntity(request)).thenReturn(user);
        Mockito.when(encryptUtils.encryptPassword(user.getPassword())).thenReturn("JohnDoe123");
        Mockito.when(usersRepository.save(user)).thenReturn(user);
        Mockito.when(usersMapper.toResponseDTO(user)).thenReturn(response);
        var userResponse = usersService.createUser(request);
        Assertions.assertThat(userResponse).isNotNull();
        Assertions.assertThat(userResponse.name()).isEqualTo(response.name());
        Assertions.assertThat(userResponse.email()).isEqualTo(response.email());
        Assertions.assertThat(userResponse.roleUser()).isEqualTo(response.roleUser());
    }

    @Test
    void shouldThrowException_WhenEmailFoundedWhenCreateUser() {
        var user = MockBuilder.buildUser();
        var request = MockBuilder.buildCreateUserRequestDTO();
        Mockito.when(usersRepository.findByEmail(request.email())).thenReturn(Optional.of(user));
        Assertions.assertThatThrownBy(() -> usersService.createUser(request))
                .isInstanceOf(GenericExceptionHandler.class)
                .hasMessageContaining(String.format("E-mail %s is already in use", request.email()));
    }

    @Test
    void shouldLoadUserSuccessfully() {
        var user = MockBuilder.buildUser();
        var request = MockBuilder.buildCreateUserRequestDTO();
        Mockito.when(usersRepository.findByEmail(request.email())).thenReturn(Optional.of(user));
        var userResponse = usersService.loadUserByUsername(request.email());
        Assertions.assertThat(userResponse).isNotNull();
        Assertions.assertThat(userResponse.getUsername()).isEqualTo(request.email());
        Assertions.assertThat(userResponse.getPassword()).isEqualTo(request.password());
    }

    @Test
    void shouldThrowException_WhenEmailNotFoundedInLoadUser() {
        var request = MockBuilder.buildCreateUserRequestDTO();
        Mockito.when(usersRepository.findByEmail(request.email())).thenReturn(Optional.empty());
        Assertions.assertThatThrownBy(() -> usersService.loadUserByUsername(request.email()))
                .isInstanceOf(EmailNotFoundExceptionHandler.class);
    }
}
