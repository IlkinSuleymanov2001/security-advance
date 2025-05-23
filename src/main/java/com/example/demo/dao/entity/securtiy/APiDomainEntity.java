package com.example.demo.dao.entity.securtiy;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Table(name = "domain_objects")
@Getter
@Setter
@ToString
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = PRIVATE)
public class APiDomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name; // Məsələn: "USER_SERVICE", "STAFF_SERVICE"
}
