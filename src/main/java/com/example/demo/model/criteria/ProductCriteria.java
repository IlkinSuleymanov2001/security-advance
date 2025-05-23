package com.example.demo.model.criteria;

import com.example.demo.model.enums.ProductCategory;
import com.example.demo.model.enums.ProductMark;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCriteria {


    @JsonProperty(value = "productCode")
    String productCode;

    @JsonProperty(value = "category")
    ProductCategory category;

    @JsonProperty(value = "createdAtFrom")
    LocalDateTime createdAtFrom;

    @JsonProperty(value = "CreatedAtTo")
    LocalDateTime CreatedAtTo;

    @JsonProperty(value = "CreatedAt")
    LocalDateTime CreatedAt;

    @JsonProperty(value = "mark")
    ProductMark mark;

    @JsonProperty(value = "priceFrom")
    Long priceFrom;
    @JsonProperty(value = "priceTo")
    Long priceTo;
    @JsonProperty(value = "price")
    Long price;

    @JsonProperty(value = "stockFrom")
    Integer stockFrom;
    @JsonProperty(value = "stockTo")
    Integer stockTo;
    @JsonProperty(value = "stock")
    Integer stock;

}
