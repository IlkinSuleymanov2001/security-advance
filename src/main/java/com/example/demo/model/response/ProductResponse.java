package com.example.demo.model.response;


import com.example.demo.model.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class ProductResponse {

    Long id;

    String name;

    String mark;

    String description;

    String productCode;

    ProductCategory category;

    Long price;

    Integer stock;

    LocalDateTime createdAt;
}
