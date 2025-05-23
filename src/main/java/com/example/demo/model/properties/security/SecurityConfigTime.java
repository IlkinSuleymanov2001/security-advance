package com.example.demo.model.properties.security;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties("jwt")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SecurityConfigTime {

    AccessTokenDto accessToken;
    RefreshTokenDto refreshToken;

}
