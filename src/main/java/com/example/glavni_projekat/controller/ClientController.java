package com.example.glavni_projekat.controller;



import com.example.glavni_projekat.model.Client;
import com.example.glavni_projekat.repository.ClientJpaRepository;
import com.example.glavni_projekat.repository.ClientRepository;
import org.apache.coyote.Response;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.xml.validation.Validator;
import java.util.List;
import java.util.regex.Pattern;

@RestController



public class ClientController {

    private ClientRepository clientRepository;


    private ClientJpaRepository clientJpaRepository;

    public ClientController(ClientRepository clientRepository, ClientJpaRepository clientJpaRepository) {
        this.clientJpaRepository = clientJpaRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping("/api/clients")
    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    @GetMapping("/api/clients/{name}")
    public List<Client> getClientByName(@PathVariable String name) {
        return clientJpaRepository.findByNameStartsWith(name);
    }

    @GetMapping("/api/clients/{id}")
    public Client getClientById(@PathVariable int id) {
        return clientJpaRepository.findById(id).get();
    }

    @GetMapping("/api/clients/{id}/text")
    public String getClientTextById(@PathVariable int id) {
        return clientRepository.readClient(id);
    }

    @PostMapping("/api/clients/register")
    public ResponseEntity<String> registerClient(Client client) {

        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(client.getEmail()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email must be valid");
        }

        regex = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
        pattern = Pattern.compile(regex);

        if (!pattern.matcher(client.getUsername()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username must be valid");
        }

//        Username consists of alphanumeric characters (a-zA-Z0-9), lowercase, or uppercase.
//        Username allowed of the dot (.), underscore (_), and hyphen (-).
//        The dot (.), underscore (_), or hyphen (-) must not be the first or last character.
//        The dot (.), underscore (_), or hyphen (-) does not appear consecutively, e.g., java..regex
//        The number of characters must be between 5 to 20.

        regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        pattern = Pattern.compile(regex);

        if (!pattern.matcher(client.getPassword()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("password must be valid");
        }

//        Password must contain at least one digit [0-9].
//        Password must contain at least one lowercase Latin character [a-z].
//        Password must contain at least one uppercase Latin character [A-Z].
//        Password must contain at least one special character like ! @ # & ( ).
//        Password must contain a length of at least 8 characters and a maximum of 20 characters.


        if (!clientJpaRepository.findByUsername(client.getUsername()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("username exists.");
        } else if (!clientJpaRepository.findByEmail(client.getEmail()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("email exists.");
        } else {
            clientRepository.insertClient(client);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful.");
        }

    }

    @PostMapping("/api/clients/login")
    public ResponseEntity<?> loginClient(Client client) {
        if (clientJpaRepository.findByUsernameAndPassword(client.getUsername(), client.getPassword()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or password incorrect.");
        } else {
            String userName = clientJpaRepository.findByUsername(client.getUsername()).get(0).getUsername();
            return ResponseEntity.status(HttpStatus.OK).body("Client with username " + userName + " logged in.");
        }
    }

    @PutMapping("/api/clients/{id}")
    public ResponseEntity<?> updateClient(@PathVariable int id, Client client) {
        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(client.getEmail()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email must be valid");
        }

        regex = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
        pattern = Pattern.compile(regex);

        if (!pattern.matcher(client.getUsername()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username must be valid");
        }

//        Username consists of alphanumeric characters (a-zA-Z0-9), lowercase, or uppercase.
//        Username allowed of the dot (.), underscore (_), and hyphen (-).
//        The dot (.), underscore (_), or hyphen (-) must not be the first or last character.
//        The dot (.), underscore (_), or hyphen (-) does not appear consecutively, e.g., java..regex
//        The number of characters must be between 5 to 20.

        regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        pattern = Pattern.compile(regex);

        if (!pattern.matcher(client.getPassword()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("password must be valid");
        }

//        Password must contain at least one digit [0-9].
//        Password must contain at least one lowercase Latin character [a-z].
//        Password must contain at least one uppercase Latin character [A-Z].
//        Password must contain at least one special character like ! @ # & ( ).
//        Password must contain a length of at least 8 characters and a maximum of 20 characters.


        if (!clientJpaRepository.findById(id).isEmpty()) {
            clientRepository.updateClient(id, client);
            return ResponseEntity.status(HttpStatus.OK).body("Client updated.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client with id doesn't exists.");
        }

    }
}




