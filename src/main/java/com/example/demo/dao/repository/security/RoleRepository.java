package com.example.demo.dao.repository.security;

import com.example.demo.dao.entity.securtiy.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
