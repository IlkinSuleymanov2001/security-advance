package com.example.demo.model.properties.security;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class AccessTokenDto {
    AccessTokenExpirationDto expiration;
}
