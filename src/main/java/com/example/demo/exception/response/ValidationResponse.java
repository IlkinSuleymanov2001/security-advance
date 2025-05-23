package com.example.demo.exception.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Map;

import static lombok.AccessLevel.PRIVATE;


@Builder
@Data
@FieldDefaults(level = PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponse {

    String message;
    String errorCode;
    Map<String, String> errors;


}
