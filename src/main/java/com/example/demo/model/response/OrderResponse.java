package com.example.demo.model.response;


import com.example.demo.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class OrderResponse {

    Long id;

    LocalDateTime orderDate;

    Float amount;

    Status status;

    Long ownerId;

    List<ProductResponse> products;


}
