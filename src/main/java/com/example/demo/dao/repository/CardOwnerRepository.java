package com.example.demo.dao.repository;

import com.example.demo.dao.entity.CardOwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardOwnerRepository extends JpaRepository<CardOwnerEntity, Long>, JpaSpecificationExecutor<CardOwnerEntity> {


}
