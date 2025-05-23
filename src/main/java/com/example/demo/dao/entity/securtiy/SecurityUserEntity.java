package com.example.demo.dao.entity.securtiy;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;


@Getter
@Setter
@ToString
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = PRIVATE)
@Table(name = "security_users")
public class SecurityUserEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @OneToOne
    @JoinColumn(name = "role_id")
    RoleEntity role;
}


