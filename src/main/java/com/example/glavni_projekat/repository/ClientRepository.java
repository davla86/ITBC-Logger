package com.example.glavni_projekat.repository;

import com.example.glavni_projekat.model.Client;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository {
    private JdbcTemplate jdbcTemplate;

    public List<Client>getAllClients() {
        String query = "SELECT id, username, role FROM clients";
         List<Client> clients = jdbcTemplate.query(
         query,
                 BeanPropertyRowMapper.newInstance(Client.class)
         );
         return clients;
    }

    public void insertClient(Client client) {
        String action = "IMSERT INTO clients ([id], [username], [password], [role]) VALUES (' " + client.getId() + " ' , ' " + client.getUsername()
                + " ' , ' " + client.getPassword() + " ' , ' " + client.getRole() + " ')";
        jdbcTemplate.execute(action);
    }
    public String readClient(int id) {
        String action = "SELECT id, username, password, role FROM logs";

        Client client = jdbcTemplate.query(
                action,
                BeanPropertyRowMapper.newInstance(Client.class)
        ).get(0);

        return client.toString();
    }



}
