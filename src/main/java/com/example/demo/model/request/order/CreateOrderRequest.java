package com.example.demo.model.request.order;

import com.example.demo.model.enums.Status;
import com.example.demo.model.request.product.ASingOrGetProductIdentifier;
import com.example.demo.model.request.user.GetUserIdentifierRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class CreateOrderRequest {

    @JsonProperty(value = "amount")
    Float amount;

    @JsonProperty(value = "status")
    Status status;

    @JsonProperty(value = "user")
    GetUserIdentifierRequest user;

    @JsonProperty(value = "products")
    List<ASingOrGetProductIdentifier> products;


}
