package com.demo.todoappjsp.repository;

import com.demo.todoappjsp.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log,Long> {
}