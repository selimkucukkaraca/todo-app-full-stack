package com.demo.todoappjsp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TodoDto {
    private String publicId;
    private String title;
    private String body;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private boolean isDone;
    private String completionDate;
    private UserDto userDto;
}
