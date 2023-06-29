package com.demo.todoappjsp.repository;

import com.demo.todoappjsp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,Long> {

    Optional<Todo> findTodoByPublicId(String publicId);
}
