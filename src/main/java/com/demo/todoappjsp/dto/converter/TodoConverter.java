package com.demo.todoappjsp.dto.converter;

import com.demo.todoappjsp.dto.TodoDto;
import com.demo.todoappjsp.model.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoConverter {

    private final UserConverter userConverter;

    public TodoConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    public TodoDto convertTodoToTodoDto(Todo from) {
        return new TodoDto(
                from.getPublicId(),
                from.getTitle(),
                from.getBody(),
                from.getCreateDate(),
                from.getUpdateDate(),
                from.isDone(),
                from.getCompletionDate(),
                userConverter.convertUserToUserDto(from.getUser())
        );
    }
}
