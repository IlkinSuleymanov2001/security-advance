package com.example.demo.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class CardResponse {

    String pan;
    LocalDate expiryDate;
    String placeHolder;
    String cvv;
    Boolean isBlock;
    Boolean isActive;
    LocalDateTime createdAt;
    LocalDateTime lastBlockDate;


}
