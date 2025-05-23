package com.example.demo.dao.repository.security;

import com.example.demo.dao.entity.securtiy.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface SPermissionRepository extends JpaRepository<PermissionEntity, Long>, JpaSpecificationExecutor<PermissionEntity> {

    @Query(value = """
             SELECT p FROM PermissionEntity p
             JOIN FETCH p.role
             JOIN FETCH p.domain
             WHERE p.role.name = :roleName AND p.domain.name = :domainName
            """)
    PermissionEntity findByRoleNameAndDomainName(
            @Param("roleName") String roleName,
            @Param("domainName") String domainName
    );
}
