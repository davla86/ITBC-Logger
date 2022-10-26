package com.example.glavni_projekat.controller;




import com.example.glavni_projekat.model.Log;
import com.example.glavni_projekat.repository.LogJpaRepository;
import com.example.glavni_projekat.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogController {
    private LogRepository logRepository;
    private LogJpaRepository logJpaRepository;

    @Autowired
    public LogController(LogRepository logRepository, LogJpaRepository logJpaRepository){
        this.logRepository = logRepository;
        this.logJpaRepository = logJpaRepository;
    }

    @GetMapping("/api/logs")
    public List<Log> getAllLogs(){return logRepository.getAllLogs();}

    @GetMapping("/api/logs/{name}")
    public List<Log> getLogsByName(@PathVariable String name){
        return logJpaRepository.findByNameStartsWith(name);
    }

    @GetMapping("/api/logs/{id}")
    public Log getLogById(@PathVariable int id){
        return logJpaRepository.findById(id).get();
    }

    @GetMapping("/api/logs/{id}/text")
    public String getLogTextById(@PathVariable int id){
        return logRepository.readLog(id);
    }

    @PostMapping("/api/logs")
    public void createLog(Log log){
        logRepository.insertLog(log);
    }

}
