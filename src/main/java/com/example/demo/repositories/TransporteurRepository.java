package com.example.demo.repositories;

import com.example.demo.entity.Transporteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransporteurRepository extends JpaRepository<Transporteur, Long> {
    // findAll(), findById(), save(), deleteById() sont automatiques !
}