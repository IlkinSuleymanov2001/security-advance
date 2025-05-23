package com.example.demo.util;

import com.example.demo.exception.AuthException;
import com.example.demo.model.constants.AuthConstants;
import com.example.demo.model.jwt.AccessTokenClaimsSet;
import com.example.demo.model.jwt.AuthCacheData;
import com.example.demo.model.jwt.RefreshTokenClaimsSet;
import com.example.demo.model.jwt.TokenValidatorFactory;
import com.example.demo.service.adapter.cache.CacheService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Date;

import static com.example.demo.model.constants.AuthConstants.RSA;
import static com.example.demo.model.constants.CacheDbNameConstants.TOKEN_CACHE;
import static com.example.demo.model.enums.ErrorInfo.USER_UNAUTHORIZED;
import static jodd.util.Base64.decode;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtil {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CacheService cacheService;
    private final TokenValidatorFactory tokenValidatorFactory;

    public static Date generateSessionExpirationTime(Integer expirationMinute) {
        return new Date(System.currentTimeMillis() + expirationMinute * 60 * 1_000);
    }

    public KeyPair generateKeyPair() {

        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA);

            keyPairGen.initialize(AuthConstants.KEY_SIZE);
            return keyPairGen.generateKeyPair();


        } catch (NoSuchAlgorithmException e) {
            log.error("Action.GenerateKeyPair.error no such Algorithm", e);
            throw new AuthException(USER_UNAUTHORIZED, 401);
        }
    }

    public <T> String generateToken(T tokenClaimSet, PrivateKey privateKey) {
        SignedJWT signedJWT;
        try {
            signedJWT = generateSignedJWT(objectMapper.writeValueAsString(tokenClaimSet), privateKey);
        } catch (Exception e) {
            log.error("Action.GenerateToken.error no such token", e);
            throw new AuthException(USER_UNAUTHORIZED, 401);
        }
        return signedJWT.serialize();
    }

    private SignedJWT generateSignedJWT(String strClaims, PrivateKey privateKey) {
        try {
            // 1. Parse JSON claims to a JWTClaimsSet
            JWTClaimsSet claimsSet = JWTClaimsSet.parse(strClaims);

            // 2. Build the header (RS256 is common)
            JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                    .type(JOSEObjectType.JWT)
                    .build();

            // 3. Create SignedJWT object
            SignedJWT signedJWT = new SignedJWT(header, claimsSet);

            // 4. Sign with private key
            JWSSigner signer = new RSASSASigner(privateKey);
            signedJWT.sign(signer);

            return signedJWT;
        } catch (Exception e) {
            log.error("Action.GenerateSignedJWT.error", e);
            throw new AuthException(USER_UNAUTHORIZED, 401);
        }
    }

    public boolean isRefreshTokenTimeExpired(RefreshTokenClaimsSet refreshTokenClaimsSet) {
        return refreshTokenClaimsSet.getExp().before(new Date());
    }

    public boolean isAccessTokenTimeExpired(AccessTokenClaimsSet accessTokenClaimsSet) {
        return accessTokenClaimsSet.getExp().before(new Date());
    }

    public boolean isRefreshTokenCountExpired(RefreshTokenClaimsSet refreshTokenClaimsSet) {
        return refreshTokenClaimsSet.getCount() <= 0;
    }


//    public void validateAccessToken(String token) {
//
//        AccessTokenClaimsSet accessTokenClaimsSet;
//        try {
//            // Token-i deserialize et
//            SignedJWT signedJWT = SignedJWT.parse(token);
//
//            // Claims-ləri çək
//            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
//
//            // Access token-i doğrula
//             accessTokenClaimsSet = objectMapper.readValue(claimsSet.toString(), AccessTokenClaimsSet.class);
//            // Access token-in vaxtının bitməsini yoxla
//            if (isAccessTokenTimeExpired(accessTokenClaimsSet)) {
//                throw new AuthException(USER_UNAUTHORIZED, 401);
//            }
//
//            // İmzanı yoxla
//            String cacheData = (String)cacheService.get(accessTokenClaimsSet.getUserId(), TOKEN_CACHE);
//            PublicKey publicKey = KeyFactory.getInstance(RSA)
//                    .generatePublic(new X509EncodedKeySpec(decode(cacheData)));
//
//            JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) publicKey);  // Public key burada istifadə edilir
//            if (!signedJWT.verify(verifier)) {
//                throw new AuthException(USER_UNAUTHORIZED, 401);
//            }
//
//        } catch (ParseException | JOSEException | IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
//            throw new AuthException(USER_UNAUTHORIZED, 401);
//        }
//
//
//    }
//
//    public RefreshTokenClaimsSet validateRefreshToken(String token) {
//        RefreshTokenClaimsSet refreshTokenClaimsSet;
//        try {
//            // Token-i deserialize et
//            SignedJWT signedJWT = SignedJWT.parse(token);
//
//            // Claims-ləri çək
//            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
//
//
//            // Refresh token-ləri `RefreshTokenClaimsSet` obyektinə çevir
//             refreshTokenClaimsSet = objectMapper.readValue(claimsSet.toString(), RefreshTokenClaimsSet.class);
//
//            // Refresh token-in vaxtının bitməsi və count limitini yoxla
//            if (isRefreshTokenTimeExpired(refreshTokenClaimsSet) || isRefreshTokenCountExpired(refreshTokenClaimsSet)) {
//                throw new AuthException(USER_UNAUTHORIZED, 401);
//            }
//            AuthCacheData authCacheData  = (AuthCacheData)cacheService.get(claimsSet.getSubject(), TOKEN_CACHE);
//            // İmzanı yoxla
//            PublicKey publicKey = KeyFactory.getInstance(RSA)
//                    .generatePublic(new X509EncodedKeySpec(decode((String)authCacheData.getData())));
//
//            // İmzanı yoxla
//            JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) publicKey);  // Public key burada istifadə edilir
//            if (!signedJWT.verify(verifier)) {
//                throw new AuthException(USER_UNAUTHORIZED, 401);
//            }
//
//            return refreshTokenClaimsSet;
//
//        } catch (ParseException | JOSEException | IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
//            throw new AuthException(USER_UNAUTHORIZED, 401);
//        }
//    }

    public <T> T validateToken(String token, Class<T> claimsClass) {
        try {
            // Token-i parse et
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

            // Claims-ləri deserialize et
            T claims = objectMapper.readValue(claimsSet.toString(), claimsClass);
            tokenValidatorFactory.tokenValidate(claims);


            // Public key-i cache-dən al
            AuthCacheData cacheData = (AuthCacheData) cacheService.get(tokenValidatorFactory.getUserId(), TOKEN_CACHE);
            PublicKey publicKey = KeyFactory.getInstance(RSA)
                    .generatePublic(new X509EncodedKeySpec(decode((String) cacheData.getData())));

            // İmzanı yoxla
            JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) publicKey);
            if (!signedJWT.verify(verifier)) {
                throw new AuthException(USER_UNAUTHORIZED, 401);
            }

            return claims;

        } catch (ParseException | JOSEException | IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.info("token validate exception {} ", e);
            throw new AuthException(USER_UNAUTHORIZED, 401);
        }
    }


}
