package com.example.demo.exception.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    String message;
    String errorCode;
}
