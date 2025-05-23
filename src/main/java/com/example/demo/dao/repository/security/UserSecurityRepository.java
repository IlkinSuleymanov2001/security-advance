package com.example.demo.dao.repository.security;

import com.example.demo.dao.entity.securtiy.SecurityUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserSecurityRepository extends JpaRepository<SecurityUserEntity, Long>, JpaSpecificationExecutor<SecurityUserEntity> {

    Optional<SecurityUserEntity> findByEmail(@Param("email") String username);

}
