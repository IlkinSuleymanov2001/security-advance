package com.example.demo.model.auth;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PUBLIC)
public class SignInDto {

    String username;
    String password;


}
