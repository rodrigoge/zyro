package br.com.app.zyro.authservice.services;

import br.com.app.zyro.authservice.db.UsersRepository;
import br.com.app.zyro.authservice.dtos.CreateUserRequestDTO;
import br.com.app.zyro.authservice.dtos.CreateUserResponseDTO;
import br.com.app.zyro.authservice.exceptions.handlers.GenericExceptionHandler;
import br.com.app.zyro.authservice.mappers.UsersMapper;
import br.com.app.zyro.authservice.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private EncryptUtils encryptUtils;

    public CreateUserResponseDTO createUser(CreateUserRequestDTO requestDTO) {
        usersRepository.findByEmail(requestDTO.email()).orElseThrow(() ->
                new GenericExceptionHandler(HttpStatus.BAD_REQUEST,
                        LocalDateTime.now(),
                        String.format("E-mail %s is already in use", requestDTO.email())
                )
        );
        var userEntity = usersMapper.toEntity(requestDTO);
        userEntity.setPassword(encryptUtils.encryptPassword(userEntity.getPassword()));
        var userSaved = usersRepository.save(userEntity);
        return usersMapper.toResponseDTO(userSaved);
    }
}
