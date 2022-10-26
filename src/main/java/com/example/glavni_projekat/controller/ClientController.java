package com.example.glavni_projekat.controller;





import com.example.glavni_projekat.model.Client;
import com.example.glavni_projekat.repository.ClientJpaRepository;
import com.example.glavni_projekat.repository.ClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {
    ClientRepository clientRepository;
    ClientJpaRepository clientJpaRepository;

    public ClientController(ClientRepository clientRepository, ClientJpaRepository clientJpaRepository){
        this.clientJpaRepository = clientJpaRepository;
        this.clientRepository = clientRepository;

    }

    @GetMapping("/api/clients")
    public List<Client> getAllClients(){
        return clientRepository.getAllClients();
    }

    @GetMapping("/api/clients/{name}")
    public List<Client> getClientByName(@PathVariable String name){
        return clientJpaRepository.findByNameStartsWith(name);
    }

    @GetMapping("/api/clients/{id}")
    public Client getClientById(@PathVariable int id){
        return clientJpaRepository.findById(id).get();
    }

    @GetMapping("/api/clients/{id}/text")
    public String getClientTextById(@PathVariable int id){
        return clientRepository.readClient(id);
    }

    @PostMapping("/api/clients")
    public void createClient(Client client){
        clientRepository.insertClient(client);
    }
}