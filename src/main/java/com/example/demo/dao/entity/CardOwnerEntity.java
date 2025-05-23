package com.example.demo.dao.entity;


import com.example.demo.model.enums.Nationality;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Table(name = "card_owners")
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor
@Builder
public class CardOwnerEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "fin")
    String fin;

    @Column(name = "legal_id")
    String legalId;

    @Column(name = "surname")
    String surname;

    @Column(name = "father_name")
    String fatherName;

    @Column(name = "nationality")
    @Enumerated(EnumType.STRING)
    Nationality nationality;

    @Column(name = "age")
    Integer age;

    @Column(name = "email")
    String email;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;


    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @Column(name = "version")
    @Version
    Long version;


    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY,
            mappedBy = "owner"

    )
    List<CardEntity> cards;


}
