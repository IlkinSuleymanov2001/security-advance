package com.example.demo.dao.repository;

import com.example.demo.dao.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {


    Optional<ProductEntity> findByProductCode(String productCode);
}
