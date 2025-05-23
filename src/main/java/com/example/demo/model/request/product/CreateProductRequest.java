package com.example.demo.model.request.product;


import com.example.demo.model.enums.ProductCategory;
import com.example.demo.model.enums.ProductMark;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateProductRequest {

    @JsonProperty(value = "name")
    String name;
    @JsonProperty(value = "description")
    String description;
    @JsonProperty(value = "category")
    ProductCategory category;
    @JsonProperty(value = "productCode")
    String productCode;
    @JsonProperty(value = "price")
    Long price;
    @JsonProperty(value = "stock")
    Integer stock;

    @JsonProperty(value = "mark")
    ProductMark mark = ProductMark.PRADA;


}
