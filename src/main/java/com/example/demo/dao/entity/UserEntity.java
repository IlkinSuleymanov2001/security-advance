package com.example.demo.dao.entity;

import com.example.demo.dao.entity.securtiy.SecurityUserEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Table(name = "users")
@Getter
@Setter
@ToString
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = PRIVATE)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "age")
    Integer age;

    @Column(name = "version")
    @Version
    Long version;

    @Column(name = "birth_place")
    String birthPlace;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "active_account")
    @Builder.Default
    Boolean activeAccount = true;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY,
            mappedBy = "user"
            // orphanRemoval = true

    )
    @ToString.Exclude
    List<OrderEntity> orders;

    @OneToOne
    @JoinColumn(name = "security_user_id")
    SecurityUserEntity securityUserEntity;


}



