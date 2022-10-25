package com.example.glavni_projekat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "logs")
public class Log {
    @Id
    private int id;
    private String message;
    private LogType logType;
    private Date createdDate;

    public Log(int id, String message, LogType logType, Date createdDate){
        this.id = id;
        this.message = message;
        this.logType = logType;
        this.createdDate = createdDate;
    }

    public Log() {

    }

    public int getId(){ return id; }
    public void setId(int id){ this.id = id; }

    public String getMessage(){return message;}
    public void setMessage(String message){this.message = message;}

    public LogType getLogType(){return logType;}
    public void setLogType(LogType logType){this.logType = logType;}

    public Date getCreatedDate(){return createdDate;}
    public void setCreatedDate(Date createdDate){this.createdDate = createdDate;}

    @Override
    public String toString(){
        return "Log{ " +
                "message: " + message + " " +
                "logType: " + logType + " " +
                "created date: " + createdDate + " }";
    }
}

enum LogType{
    Error,
    Warning,
    Info
}
