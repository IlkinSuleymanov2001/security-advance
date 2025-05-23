package com.example.demo.dao.entity;


import com.example.demo.model.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "mark")
    String mark;

    @Column(name = "description")
    String description;

    @Column(name = "product_code")
    String productCode;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    ProductCategory category;

    @Column(name = "price")
    Long price;

    @Column(name = "stock")
    Integer stock;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
            , mappedBy = "products"
    )
    List<OrderEntity> orders;

    public ProductEntity() {
        orders = new ArrayList<>();
    }

    public ProductEntity addOrder(OrderEntity order) {
        orders.add(order);
        return this;
    }


}
