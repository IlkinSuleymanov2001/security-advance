package com.example.demo.dao.repository;

import com.example.demo.dao.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<CardEntity, Long>, JpaSpecificationExecutor<CardEntity> {

    List<CardEntity> findByOwner_Id(@Param("owner_id") Long userId);


}
