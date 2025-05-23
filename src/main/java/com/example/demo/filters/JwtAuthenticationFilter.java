package com.example.demo.filters;

import com.example.demo.model.jwt.AccessTokenClaimsSet;
import com.example.demo.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // Bearer tokeni yoxla
        if (Objects.nonNull(authHeader) && authHeader.startsWith("Bearer ")) {

            AccessTokenClaimsSet accessTokenClaimsSet = jwtUtil.validateToken(
                    authHeader.substring(7)
                    , AccessTokenClaimsSet.class);

            var authToken = new UsernamePasswordAuthenticationToken(
                    accessTokenClaimsSet.getUserId(),
                    null,
                    List.of(new SimpleGrantedAuthority(accessTokenClaimsSet.getRoles().get(0)))
            );

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);


        }

        filterChain.doFilter(request, response);
    }
}