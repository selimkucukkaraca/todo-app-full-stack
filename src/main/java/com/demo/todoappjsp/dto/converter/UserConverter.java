package com.demo.todoappjsp.dto.converter;

import com.demo.todoappjsp.dto.UserDto;
import com.demo.todoappjsp.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto convertUserToUserDto(User from) {
        return new UserDto(
                from.getUsername(),
                from.getMail(),
                from.getCreateDate(),
                from.getUpdateDate()
        );
    }
}
