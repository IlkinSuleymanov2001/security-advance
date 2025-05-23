package com.example.demo.dao.repository;

import com.example.demo.dao.entity.OrderEntity;
import com.example.demo.model.enums.Status;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {

    @Override
    @EntityGraph(attributePaths = {"products", "user"})
    Optional<OrderEntity> findById(@Param("id") Long userId);

    //@EntityGraph(attributePaths = {"products","user"})
    @Query("select distinct  o from OrderEntity o join fetch o.products join fetch  o.user ")
    List<OrderEntity> findByStatus(Status status);

}
