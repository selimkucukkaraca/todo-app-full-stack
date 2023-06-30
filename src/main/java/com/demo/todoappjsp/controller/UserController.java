package com.demo.todoappjsp.controller;

import com.demo.todoappjsp.dto.UserDto;
import com.demo.todoappjsp.dto.request.UserCreateRequest;
import com.demo.todoappjsp.dto.request.UserUpdateRequest;
import com.demo.todoappjsp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserCreateRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.save(request));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam String mail){
        userService.delete(mail);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestParam String mail,
                                              @RequestBody Optional<UserUpdateRequest> request){
        return ResponseEntity.ok(userService.updateUser(mail,request));
    }

}
