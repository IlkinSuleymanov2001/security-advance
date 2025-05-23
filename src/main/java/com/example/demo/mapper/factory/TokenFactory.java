package com.example.demo.mapper.factory;


import com.example.demo.model.jwt.AccessTokenClaimsSet;
import com.example.demo.model.jwt.RefreshTokenClaimsSet;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import static com.example.demo.model.constants.AuthConstants.ISSUER;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenFactory {


    public static AccessTokenClaimsSet buildAccessTokenClaimsSet(String userId,
                                                                 List<String> roles,
                                                                 Date expirationTime) {

        return AccessTokenClaimsSet.builder()
                .iss(ISSUER)
                .userId(userId)
                .roles(roles)
                .createdTime(new Date())
                .exp(expirationTime)
                .build();
    }

    public static RefreshTokenClaimsSet buildRefreshTokenClaimsSet(String userId,
                                                                   List<String> roles,
                                                                   int refreshTokenExpirationTime, Date expirationTime) {
        return RefreshTokenClaimsSet.builder()
                .iss(ISSUER)
                .userId(userId)
                .createdTime(new Date())
                .exp(expirationTime)
                .count(refreshTokenExpirationTime)
                .roles(roles)
                .build();
    }
}
