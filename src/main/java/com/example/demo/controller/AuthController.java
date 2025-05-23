package com.example.demo.controller;

import com.example.demo.model.auth.SignInDto;
import com.example.demo.model.jwt.TokenDto;
import com.example.demo.service.impl.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // Endpoint to sign in and generate a token
    @PostMapping("/signin")
    @ResponseStatus(OK)
    public ResponseEntity<TokenDto> signIn(@RequestBody SignInDto signInDto) {
        TokenDto tokenDto = authService.singIn(signInDto);
        return new ResponseEntity<>(tokenDto, HttpStatus.OK);
    }

    // Endpoint to verify the token
//    @PostMapping("/verify")
//    public ResponseEntity<String> verifyToken(@RequestParam("token") String token) {
//        authService.verifyToken(token);
//        return new ResponseEntity<>("Token is valid", HttpStatus.OK);
//    }

    // Endpoint to refresh the token
    @PostMapping("/refresh")
    @ResponseStatus(OK)
    public ResponseEntity<TokenDto> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        TokenDto newToken = authService.refreshToken(refreshToken);
        return new ResponseEntity<>(newToken, HttpStatus.OK);
    }

    // Endpoint to refresh the token
    @GetMapping()
    @ResponseStatus(OK)
    public String test() {
        return "online";
    }
}