package com.example.demo.service.impl.security;


import com.example.demo.dao.entity.securtiy.CustomUserDetails;
import com.example.demo.model.auth.SignInDto;
import com.example.demo.model.jwt.TokenDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class AuthService {


    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    @Value("${jwt.refreshToken.expiration.count}")
    private int refreshTokenExpireCount;

    public TokenDto singIn(SignInDto signInDto) {

        Authentication authenticated = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInDto.getUsername(), signInDto.getPassword()));


        CustomUserDetails principal = (CustomUserDetails) authenticated.getPrincipal();
        List<String> roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return tokenService.generateToken(1L, roles, refreshTokenExpireCount);
    }

    public TokenDto refreshToken(String refreshToken) {
        return tokenService.refreshToken(refreshToken);
    }


}
