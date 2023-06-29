package com.demo.todoappjsp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Todo extends BaseEntity {

    private String publicId = UUID.randomUUID().toString();
    private String title;
    private String body;
    @ManyToOne
    private User user;
    private String completionDate;
    private boolean isDone = false;


    public Todo(String title, String body, User user,String completionDate) {
        this.title = title;
        this.body = body;
        this.user = user;
        this.completionDate = completionDate;
    }
}