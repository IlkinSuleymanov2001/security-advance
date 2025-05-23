package com.example.demo.dao.repository;

import com.example.demo.dao.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, Long>, JpaSpecificationExecutor<Travel> {

    List<Travel> findAllByCountry(String country);
}
