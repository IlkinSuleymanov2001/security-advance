package com.example.demo.model.client;


import com.example.demo.model.enums.Nationality;
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
public class CardOwnerResponseDto {

    Long id;

    String name;

    String fin;

    String legalId;

    String surname;

    String fatherName;

    Nationality nationality;

    Integer age;

    String email;

    LocalDateTime createdAt;


    LocalDateTime updatedAt;
}
