package com.demo.todoappjsp.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends BaseEntity{

    private String username;
    private String password;
    private String mail;

    public User(String username, String password, String mail) {
        this.username = username;
        this.password = password;
        this.mail = mail;
    }
}