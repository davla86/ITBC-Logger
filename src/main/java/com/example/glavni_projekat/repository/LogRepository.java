package com.example.glavni_projekat.repository;

import com.example.glavni_projekat.model.Log;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LogRepository {
    private JdbcTemplate jdbcTemplate;

    public List<Log>getAllLogs() {
        String query = "SELECT id, message, logType, createdDate FROM logs";
        List<Log> logs = jdbcTemplate.query(
                query,
                BeanPropertyRowMapper.newInstance(Log.class)
        );
        return logs;
    }

        public void insertLog(Log log){
        String action = "INSERT INTO logs ([id], [message], [logType], [createdDate]) VALUES ( ' " + log.getId() + " ', ' "
                + log.getMessage() + " ' , ' " + log.getLogType() +  " ' , ' " + log.getCreatedDate() + " ' ) ";

        jdbcTemplate.execute(action);

    }
    public String readLog(int id){
        String action = "SELECT id, message, logType, createdDate FROM logs";

        Log log = jdbcTemplate.query(
                action,
                BeanPropertyRowMapper.newInstance(Log.class)
        ).get(0);

        return log.toString();
    }

}
