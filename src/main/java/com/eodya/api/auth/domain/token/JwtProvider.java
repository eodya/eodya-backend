package com.eodya.api.auth.domain.token;

import com.eodya.api.auth.domain.config.property.JwtProperties;
import com.eodya.api.auth.exception.AuthException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

import static com.eodya.api.auth.exception.AuthExceptionCode.*;

@Slf4j
@Component
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private final SecretKey secretKey;

    public JwtProvider(final JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public AuthTokens createLoginToken(String subject) {
        String accessToken = generateToken(subject, jwtProperties.getAccessTokenExpirationTime());
        String refreshToken = generateToken("", jwtProperties.getRefreshTokenExpirationTime());

        return AuthTokens.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthTokens createRegisterToken(String subject) {
        String registerToken = generateToken(subject, jwtProperties.getRegisterTokenExpirationTime());

        return AuthTokens.builder()
                .accessToken(registerToken)
                .build();
    }

    private String generateToken(String subject, Long expirationTime) {
        Date now = new Date();

        return Jwts.builder()
                .subject(subject)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expirationTime))
                .signWith(secretKey)
                .compact();
    }

    public String regenerateAccessToken(String subject) {
        return generateToken(subject, jwtProperties.getAccessTokenExpirationTime());
    }

    public String getSubject(String token) {
        return parseToken(token)
                .getPayload()
                .getSubject();
    }

    private Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
    }

    public void validateAccessToken(String accessToken) {
        try {
            parseToken(accessToken);
        } catch (final ExpiredJwtException e) {
            throw new AuthException(AUTH_EXPIRED_ACCESS_TOKEN, accessToken);
        } catch (final JwtException | IllegalArgumentException e) {
            throw new AuthException(AUTH_INVALID_ACCESS_TOKEN, accessToken);
        }
    }

    private void validateRefreshToken(String refreshToken) {
        try {
            parseToken(refreshToken);
        } catch (final ExpiredJwtException e) {
            throw new AuthException(AUTH_EXPIRED_REFRESH_TOKEN, refreshToken);
        } catch (final JwtException | IllegalArgumentException e) {
            throw new AuthException(AUTH_INVALID_REFRESH_TOKEN, refreshToken);
        }
    }

    public void validateRegisterToken(String registerToken) {
        try {
            parseToken(registerToken);
        } catch (final ExpiredJwtException e) {
            throw new AuthException(AUTH_EXPIRED_REGISTER_TOKEN, registerToken);
        } catch (final JwtException | IllegalArgumentException e) {
            throw new AuthException(AUTH_INVALID_REGISTER_TOKEN, registerToken);
        }
    }

    public boolean isValidRefreshAndInvalidAccess(String refreshToken, String accessToken) {
        validateRefreshToken(refreshToken);

        try {
            validateAccessToken(accessToken);
        } catch (final AuthException e) {
            return true;
        }

        return false;
    }

    public boolean isValidRefreshAndValidAccess(String refreshToken, String accessToken) {
        try {
            validateRefreshToken(refreshToken);
            validateAccessToken(accessToken);

            return true;
        } catch (final JwtException e) {
            return false;
        }
    }
}
