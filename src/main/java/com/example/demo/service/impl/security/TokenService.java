package com.example.demo.service.impl.security;


import com.example.demo.mapper.factory.TokenFactory;
import com.example.demo.model.jwt.AuthCacheData;
import com.example.demo.model.jwt.RefreshTokenClaimsSet;
import com.example.demo.model.jwt.TokenDto;
import com.example.demo.model.properties.security.SecurityConfigTime;
import com.example.demo.service.adapter.cache.CacheService;
import com.example.demo.util.JwtUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.util.List;

import static com.example.demo.model.constants.CacheDbNameConstants.TOKEN_CACHE;
import static java.lang.Long.parseLong;
import static jodd.util.Base64.encodeToString;

@Service
@RequiredArgsConstructor
@Data
public class TokenService {


    private final JwtUtil jwtUtil;
    private final CacheService cacheService;
    private final SecurityConfigTime securityConfigTime;
    @Value("${jwt.accessToken.expiration.time}")
    private int accessTokenExpirationTime;
    @Value("${jwt.refreshToken.expiration.time}")
    private int refreshTokenExpirationTime;

    public static Long getUserEntityId() {
        return Long.parseLong((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public static String getUserEntityRole() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .iterator()
                .next()
                .getAuthority();


    }

    public TokenDto generateToken(Long userID, List<String> roles, int refreshTokenExpireCount) {

        var accessTokenClaimsSet = TokenFactory.buildAccessTokenClaimsSet(userID.toString(),
                roles,
                JwtUtil.generateSessionExpirationTime(accessTokenExpirationTime));

        var refreshTokenClaimsSet = TokenFactory.buildRefreshTokenClaimsSet(userID.toString(),
                roles,
                refreshTokenExpireCount,
                JwtUtil.generateSessionExpirationTime(refreshTokenExpirationTime));

        KeyPair keyPair = jwtUtil.generateKeyPair();

        String accessToken = jwtUtil.generateToken(accessTokenClaimsSet, keyPair.getPrivate());
        String refreshToken = jwtUtil.generateToken(refreshTokenClaimsSet, keyPair.getPrivate());


        String publicKeyToEncode = encodeToString(keyPair.getPublic().getEncoded());
        AuthCacheData authCacheData = AuthCacheData.
                builder()
                .data(publicKeyToEncode)
                .build();
        cacheService.saveToCache(userID.toString(),
                authCacheData
                , TOKEN_CACHE);

        return TokenDto.of(accessToken, refreshToken);

    }

    public TokenDto refreshToken(String refreshToken) {
        RefreshTokenClaimsSet refreshTokenClaimsSet = jwtUtil.validateToken(refreshToken, RefreshTokenClaimsSet.class);

        return generateToken(parseLong(refreshTokenClaimsSet.getUserId()),
                refreshTokenClaimsSet.getRoles(),
                refreshTokenClaimsSet.getCount() - 1);
    }


}

