package com.example.demo.dao.entity;


import com.example.demo.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @CreationTimestamp
    @Column(name = "order_date")
    LocalDateTime orderDate;


    @Column(name = "amount")
    Float amount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})

    @JoinColumn(name = "user_id")
    @ToString.Exclude
    UserEntity user;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY


    )
    @JoinTable(name = "order_line",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @ToString.Exclude
    List<ProductEntity> products;


    public OrderEntity() {
        products = new ArrayList<>();
    }

    public OrderEntity addProduct(ProductEntity product) {
        products.add(product);
        return this;
    }

    public OrderEntity addProduct(List<ProductEntity> product) {
        product.forEach(this::addProduct);
        return this;
    }

}

