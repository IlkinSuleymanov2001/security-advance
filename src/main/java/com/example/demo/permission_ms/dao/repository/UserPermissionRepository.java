package com.example.demo.permission_ms.dao.repository;

import com.example.demo.permission_ms.dao.entity.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> /*,JpaSpecificationExecutor<UserPermission>*/ {

    Optional<UserPermission> findByUserIdAndPermissionNameAndProductName(String userId, String permissionName, String productName);
}
