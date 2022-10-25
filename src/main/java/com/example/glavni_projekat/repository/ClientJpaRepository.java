package com.example.glavni_projekat.repository;


import com.example.glavni_projekat.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientJpaRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findById(int id);

    List<Client> findByNameStartsWith(String name);
}
