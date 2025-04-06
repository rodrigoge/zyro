package br.com.app.zyro.authservice.services;

import br.com.app.zyro.authservice.db.UsersRepository;
import br.com.app.zyro.authservice.dtos.CreateUserRequestDTO;
import br.com.app.zyro.authservice.dtos.CreateUserResponseDTO;
import br.com.app.zyro.authservice.exceptions.handlers.EmailNotFoundExceptionHandler;
import br.com.app.zyro.authservice.exceptions.handlers.GenericExceptionHandler;
import br.com.app.zyro.authservice.mappers.UsersMapper;
import br.com.app.zyro.authservice.utils.DateFormatterUtils;
import br.com.app.zyro.authservice.utils.EncryptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private EncryptUtils encryptUtils;

    public CreateUserResponseDTO createUser(CreateUserRequestDTO requestDTO) {
        usersRepository.findByEmail(requestDTO.email()).ifPresent(user -> {
            throw new GenericExceptionHandler(HttpStatus.BAD_REQUEST,
                    DateFormatterUtils.format(LocalDateTime.now()),
                    String.format("E-mail %s is already in use", requestDTO.email())
            );
        });
        var userEntity = usersMapper.toEntity(requestDTO);
        userEntity.setPassword(encryptUtils.encryptPassword(userEntity.getPassword()));
        userEntity.setRoleUser(requestDTO.roleUser());
        var userSaved = usersRepository.save(userEntity);
        return usersMapper.toResponseDTO(userSaved);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByEmail(username).orElseThrow(() -> new EmailNotFoundExceptionHandler(
                HttpStatus.BAD_REQUEST,
                DateFormatterUtils.format(LocalDateTime.now()),
                username
        ));
    }
}
