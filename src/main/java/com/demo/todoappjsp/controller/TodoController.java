package com.demo.todoappjsp.controller;

import com.demo.todoappjsp.dto.TodoDto;
import com.demo.todoappjsp.dto.request.TodoCreateRequest;
import com.demo.todoappjsp.dto.request.TodoUpdateRequest;
import com.demo.todoappjsp.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/todo")
@CrossOrigin
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoDto> save(@Valid @RequestBody TodoCreateRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todoService.save(request));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam String publicId){
        todoService.deleteByPublicId(publicId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity <List<TodoDto>> getAll(@RequestParam int size, @RequestParam int page){
        return ResponseEntity
                .ok(todoService.getAll(size,page));
    }

    @PutMapping("/update")
    public ResponseEntity<TodoDto> updateTodo(@RequestParam String publicId,
                                              @RequestBody Optional<TodoUpdateRequest> request){
        return ResponseEntity.ok(todoService.updateTodo(publicId,request));
    }

    @PutMapping("/update-done-status")
    public ResponseEntity<?> getTodoByPublicId(@RequestParam(value = "publicId") String publicId, @RequestParam(value = "status") boolean status){
        todoService.updateTodoDoneStatus(publicId, status);
        return ResponseEntity
                .noContent()
                .build();
    }

}
