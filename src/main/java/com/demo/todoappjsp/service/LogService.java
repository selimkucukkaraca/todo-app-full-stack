package com.demo.todoappjsp.service;

import com.demo.todoappjsp.model.Log;
import com.demo.todoappjsp.repository.LogRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void save(Log log){
        logRepository.save(log);
    }

    public void writeTxtFile() throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("log.txt", true))) {

            logRepository.findAll()
                            .forEach(log -> {
                                try {
                                    bufferedWriter.write(String.valueOf(log));
                                    bufferedWriter.write("\n");
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });

        }
    }
}