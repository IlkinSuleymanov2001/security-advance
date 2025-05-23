package com.example.demo.model.jwt;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class TokenDto {
    private String accessToken;
    private String refreshToken;
}
