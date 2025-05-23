package com.example.demo.dao.entity;


import com.example.demo.dao.entity.base.BaseEntity;
import com.example.demo.model.enums.CardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PRIVATE;


@Data
@Entity
@Table(name = "cards")
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor
@Builder
public class CardEntity extends BaseEntity<Long> {

//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    @Column(name = "id")
//    Long id;

    @Column(name = "cvv")
    String cvv;

    @Column(name = "place_holder")
    String placeHolder;

    @Column(name = "pan")
    String pan;

    @Column(name = "pin")
    byte[] pin;

    @Column(name = "card_type")
    @Enumerated(EnumType.STRING)
    CardType cardType;

    @Column(name = "expiry_date")
    LocalDate expiryDate;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "is_block")
    @Builder.Default
    Boolean isBlock = true;

    @Column(name = "is_active")
    @Builder.Default
    Boolean isActive = false;

    @Column(name = "last_block_date")
    LocalDateTime lastBlockDate;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @ManyToOne(fetch = LAZY, cascade = {PERSIST, MERGE})
    @JoinColumn(name = "owner_id")
    CardOwnerEntity owner;

    @Column(name = "balance")
    AtomicLong balance = new AtomicLong(0);  // Default value 0


}
