package com.demo.todoappjsp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequest {
    @Email
    private String mail;
    @NotBlank
    private String password;
}
