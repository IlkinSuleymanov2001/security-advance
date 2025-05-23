package com.example.demo.dao.repository;

import com.example.demo.dao.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Long>, JpaSpecificationExecutor<Guest> {

    Optional<Guest> findByLegalId(String legalId);
}
