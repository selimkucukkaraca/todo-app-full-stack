package com.demo.todoappjsp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TodoCreateRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String body;
    @Email
    private String userMail;
    @NotBlank
    private String completionDate;
}
