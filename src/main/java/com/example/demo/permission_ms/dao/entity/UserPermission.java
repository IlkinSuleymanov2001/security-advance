package com.example.demo.permission_ms.dao.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user_permissions")
@AllArgsConstructor
@NoArgsConstructor
public class UserPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "permission_name")
    private String permissionName;
}
