package com.example.glavni_projekat.repository;


import com.example.glavni_projekat.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.List;
import java.util.Optional;

@Repository

public interface ClientJpaRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findById(int id);

    List<Client> findByNameStartsWith(String name);

    List<Client> findByUsername(String username);

    List<Client> findByEmail(String email);

    List<Client> findByUsernameAndPassword(String username,
                                           String password);
}