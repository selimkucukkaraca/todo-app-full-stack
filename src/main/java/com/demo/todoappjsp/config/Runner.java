package com.demo.todoappjsp.config;

import com.demo.todoappjsp.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final LogService logService;

    @Override
    public void run(String... args) throws Exception {
        logService.writeTxtFile();
    }
}
