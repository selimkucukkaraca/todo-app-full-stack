package com.demo.todoappjsp.service;

import com.demo.todoappjsp.dto.UserDto;
import com.demo.todoappjsp.dto.converter.UserConverter;
import com.demo.todoappjsp.dto.request.UserCreateRequest;
import com.demo.todoappjsp.dto.request.UserUpdateRequest;
import com.demo.todoappjsp.exception.NotFoundException;
import com.demo.todoappjsp.exception.generic.GenericExistException;
import com.demo.todoappjsp.model.Log;
import com.demo.todoappjsp.model.LogType;
import com.demo.todoappjsp.model.User;
import com.demo.todoappjsp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final LogService logService;

    public UserService(UserRepository userRepository, UserConverter userConverter, LogService logService) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.logService = logService;
    }

    public UserDto save(UserCreateRequest request) {
        existUserByMail(request.getMail());
        var saved = new User(
                request.getUsername(),
                request.getPassword(),
                request.getMail()
        );


        userRepository.save(saved);
        logService.save(new Log("User successfully create : " + saved.getMail() , LogType.CREATE));
        return userConverter.convertUserToUserDto(saved);
    }

    public void delete(String mail) {
        var fromUser = getUserByMail(mail);
        userRepository.delete(fromUser);
        logService.save(new Log("User successfully deleted : " + mail, LogType.DELETE));

    }




    public UserDto updateUser(String mail, Optional<UserUpdateRequest> request) {
        UserUpdateRequest userUpdateRequest = request.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, ""));
        var fromDbUser = getUserByMail(mail);
        fromDbUser.setUsername(userUpdateRequest.getUsername());
        fromDbUser.setPassword(userUpdateRequest.getPassword());
        userRepository.save(fromDbUser);
        logService.save(new Log("User successfully update  : " + mail, LogType.UPDATE));


        return userConverter.convertUserToUserDto(fromDbUser);
    }

    protected User getUserByMail(String mail) {
        return userRepository.findUserByMail(mail)
                .orElseThrow(() -> new NotFoundException("User not fount, mail : " + mail));
    }

    private void existUserByMail(String mail){
        if (userRepository.existsUserByMail(mail)) {
            throw new GenericExistException("user already exist , mail : " + mail);
        }

    }
}
