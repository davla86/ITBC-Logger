package com.example.glavni_projekat.repository;

import com.example.glavni_projekat.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LogJpaRepository extends JpaRepository<Log, Integer> {
    Optional<Log> findById(int id);

    List<Log> findByNameStartsWith(String name);
}

