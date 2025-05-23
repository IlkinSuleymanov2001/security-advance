package com.example.demo.model.jwt;

import com.example.demo.exception.AuthException;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.example.demo.model.enums.ErrorInfo.USER_UNAUTHORIZED;

@Component

public class TokenValidatorFactory {

    private AccessTokenClaimsSet accessTokenClaimsSet;
    private RefreshTokenClaimsSet refreshTokenClaimsSet;

    @Getter
    private String userId;

    public void tokenValidate(Object object) {

        if (object instanceof RefreshTokenClaimsSet refresh) {
            refreshTokenClaimsSet = refresh;
            userId = refresh.getUserId();
            if (isRefreshTokenCountExpired() || isRefreshTokenTimeExpired()) {
                throw new AuthException(USER_UNAUTHORIZED, 401);
            }

        } else if (object instanceof AccessTokenClaimsSet access) {
            accessTokenClaimsSet = access;
            userId = access.getUserId();
            if (isAccessTokenTimeExpired()) {
                throw new AuthException(USER_UNAUTHORIZED, 401);
            }

        }

    }


    private boolean isRefreshTokenTimeExpired() {
        return refreshTokenClaimsSet.getExp().before(new Date());
    }

    private boolean isAccessTokenTimeExpired() {
        return accessTokenClaimsSet.getExp().before(new Date());
    }

    private boolean isRefreshTokenCountExpired() {
        return refreshTokenClaimsSet.getCount() <= 0;
    }
}
