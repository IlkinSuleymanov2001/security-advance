package com.example.demo.dao.entity.securtiy;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;


@Table(name = "security_permissions")
@Getter
@Setter
@ToString
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = PRIVATE)
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    RoleEntity role;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    APiDomainEntity domain;

    @Column(name = "read")
    boolean read;
    @Column(name = "delete")
    boolean delete;
    @Column(name = "write")
    boolean write;


}
