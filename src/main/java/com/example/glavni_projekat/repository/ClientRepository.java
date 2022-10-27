package com.example.glavni_projekat.repository;



import com.example.glavni_projekat.model.Client;
import com.fasterxml.classmate.util.ConcurrentTypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.TreeMap;

@Repository
public class ClientRepository {

    private JdbcTemplate jdbcTemplate;


    public List<Client> getAllClients(){
        String query = "SELECT id, username, role FROM clients";

        List<Client> clients = jdbcTemplate.query(
                query,
                BeanPropertyRowMapper.newInstance(Client.class)
        );

        return clients;
    }

    public void insertClient(Client client){
        String action = "INSERT INTO clients ([id], [username], [password], [email], [role]) VALUES ('"
                + client.getId() + "','"+ client.getUsername() + "','"+ client.getPassword() + "','" + client.getEmail() + "','"+ client.getRole() + "')";

        jdbcTemplate.execute(action);
    }

    public String readClient(int id){
        String action = "SELECT id, username, password, role FROM logs";

        Client client = jdbcTemplate.query(
                action,
                BeanPropertyRowMapper.newInstance(Client.class)
        ).get(0);

        return client.toString();
    }

    public void updateClient(int id, Client client){
        String action = "UPDATE clients " +
                "        SET username = " + client.getUsername() +", password = " + client.getPassword() + ", email = " + client.getEmail() + ", role = " + client.getRole() +
                "        WHERE id = " + id;

        jdbcTemplate.execute(action);
    }
}
