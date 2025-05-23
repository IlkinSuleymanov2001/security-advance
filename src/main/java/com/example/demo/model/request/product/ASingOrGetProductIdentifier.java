package com.example.demo.model.request.product;


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
public class ASingOrGetProductIdentifier {

    @JsonProperty(value = "productId")
    Long productId;

    @JsonProperty(value = "productCode")
    String productCode;

    @JsonProperty(value = "discountRate")
    Double discountRate;
}
