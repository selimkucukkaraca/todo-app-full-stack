package com.demo.todoappjsp.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Log extends BaseEntity{

    private String description;
    @Enumerated(EnumType.STRING)
    private LogType logType;

    public Log(String description, LogType logType) {
        this.description = description;
        this.logType = logType;
    }
}
