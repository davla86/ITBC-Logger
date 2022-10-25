package com.example.glavni_projekat.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "clients")
public class Client {
    @Id
    private int id;
    private String username;
    private String password;
    private String role;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role = role;
    }

    @Override
    public String toString(){
        return "Client{ " +
                "username: " + username + " " +
                "role: " + role + " }";
    }
}