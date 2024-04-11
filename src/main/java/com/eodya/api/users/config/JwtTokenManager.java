package com.eodya.api.users.config;

import com.eodya.api.users.exception.AuthException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;


import static com.eodya.api.users.exception.AuthExceptionCode.EXPIRED_TOKEN;
import static com.eodya.api.users.exception.AuthExceptionCode.INVALID_TOKEN;


@RequiredArgsConstructor
@Service
public class JwtTokenManager {

    private final AuthConfig authConfig;
    private final ZoneId KST = ZoneId.of("Asia/Seoul");

    public String createAccessToken(Long userId) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(authConfig.getJwtSecretKey());
        SecretKeySpec signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        Instant exp = ZonedDateTime.now(KST).toLocalDateTime().plusHours(6).atZone(KST).toInstant();

        return Jwts.builder()
                .setSubject(Long.toString(userId))
                .setExpiration(Date.from(exp))
                .signWith(signatureAlgorithm, signingKey)
                .compact();
    }

    public String createRefreshToken(Long userId) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(authConfig.getJwtSecretKey());
        SecretKeySpec signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        Instant exp = ZonedDateTime.now(KST).toLocalDateTime().plusDays(14).atZone(KST).toInstant();

        return Jwts.builder()
                .setSubject(Long.toString(userId))
                .setExpiration(Date.from(exp))
                .signWith(signatureAlgorithm, signingKey)
                .compact();
    }

    public String getUserIdFromAuthToken (String token) {
        try {
            val claims = getClaimsFromToken(token);

            return claims.getSubject();
        } catch (SignatureException | ExpiredJwtException e) {
            throw new AuthException(INVALID_TOKEN);
        }
    }

    public boolean verifyAuthToken (String token) {
        try {
            getClaimsFromToken(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new AuthException(EXPIRED_TOKEN);
        } catch (SignatureException e) {
            throw new AuthException(INVALID_TOKEN);
        }
    }

    private Claims getClaimsFromToken (String token) throws SignatureException {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(authConfig.getJwtSecretKey()))
                .parseClaimsJws(token)
                .getBody();
    }

}
