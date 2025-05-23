package com.example.demo.model.request.card;

import com.example.demo.model.enums.Nationality;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;


@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class CardOwnerRequest {
    @JsonProperty(value = "name")
    String name;

    @JsonProperty(value = "fin")
    String fin;

    @JsonProperty(value = "legalId")
    String legalId;

    @JsonProperty(value = "surname")
    String surname;

    @JsonProperty(value = "fatherName")
    String fatherName;

    @JsonProperty(value = "nationality")
    Nationality nationality;

    @JsonProperty(value = "age")
    Integer age;

    @JsonProperty(value = "email")
    String email;


}
