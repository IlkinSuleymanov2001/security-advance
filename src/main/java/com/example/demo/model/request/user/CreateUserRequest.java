package com.example.demo.model.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CreateUserRequest {


    @JsonProperty(value = "name")
    String name;

    @JsonProperty(value = "email")
    String email;

    @JsonProperty(value = "password")
    String password;

    @JsonProperty(value = "birthPlace")
    String birthPlace;

    @JsonProperty(value = "age")
    Integer age;

}
