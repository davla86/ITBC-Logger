package com.example.glavni_projekat.repository;

import com.example.glavni_projekat.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
@Repository
public interface LogJpaRepository extends JpaRepository<Log, Integer> {
    Optional<Log> findById(int id);

    List<Log> findByNameStartsWith(String name);
}

