package com.demo.todoappjsp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class UserDto {
    private String username;
    private String mail;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
