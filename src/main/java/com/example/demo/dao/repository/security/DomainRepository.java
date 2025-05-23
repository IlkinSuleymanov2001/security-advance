package com.example.demo.dao.repository.security;

import com.example.demo.dao.entity.securtiy.APiDomainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainRepository extends JpaRepository<APiDomainEntity, Long> {
}
