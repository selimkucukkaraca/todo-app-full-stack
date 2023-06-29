package com.demo.todoappjsp.service;

import com.demo.todoappjsp.dto.TodoDto;
import com.demo.todoappjsp.dto.converter.TodoConverter;
import com.demo.todoappjsp.dto.request.TodoCreateRequest;
import com.demo.todoappjsp.dto.request.TodoUpdateRequest;
import com.demo.todoappjsp.exception.NotFoundException;
import com.demo.todoappjsp.model.Log;
import com.demo.todoappjsp.model.LogType;
import com.demo.todoappjsp.model.Todo;
import com.demo.todoappjsp.model.User;
import com.demo.todoappjsp.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Slf4j
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoConverter todoConverter;
    private final UserService userService;
    private final LogService logService;

    public TodoService(TodoRepository todoRepository, TodoConverter todoConverter,
                       UserService userService, LogService logService) {
        this.todoRepository = todoRepository;
        this.todoConverter = todoConverter;
        this.userService = userService;
        this.logService = logService;
    }

    public TodoDto save(TodoCreateRequest request) {
        User user = userService.getUserByMail(request.getUserMail());

        var saved = new Todo(
                request.getTitle(),
                request.getBody(),
                user,
                request.getCompletionDate()
        );

        todoRepository.save(saved);
        logService.save(new Log("Todo successfully create : " + saved.getTitle() , LogType.CREATE));
        return todoConverter.convertTodoToTodoDto(saved);
    }

    public void deleteByPublicId(String publicId) {
        var fromTodo = getTodoByPublicId(publicId);
        todoRepository.delete(fromTodo);
        logService.save(new Log("Todo successfully deleted : " + publicId, LogType.DELETE));
    }

    public void updateTodoDoneStatus(String publicId, boolean status) {
        var fromDbTodo = getTodoByPublicId(publicId);
        fromDbTodo.setDone(status);
        todoRepository.save(fromDbTodo);
        logService.save(new Log("Todo successfully is done update  : " + publicId, LogType.UPDATE));

    }

    public TodoDto updateTodo(String publicId, Optional<TodoUpdateRequest> request) {
        TodoUpdateRequest todoUpdateRequest = request.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, ""));
        var fromDbTodo = getTodoByPublicId(publicId);
        fromDbTodo.setBody(todoUpdateRequest.getBody());
        fromDbTodo.setTitle(todoUpdateRequest.getTitle());
        todoRepository.save(fromDbTodo);
        logService.save(new Log("Todo successfully update  : " + publicId, LogType.UPDATE));

        return todoConverter.convertTodoToTodoDto(fromDbTodo);
    }

    protected Todo getTodoByPublicId(String publicId) {
        return todoRepository.findTodoByPublicId(publicId)
                .orElseThrow(() -> new NotFoundException("Todo not fount, public id : " + publicId));
    }

}