package com.eodya.api.auth.domain.config.resolver;

import com.eodya.api.auth.exception.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.eodya.api.auth.exception.AuthExceptionCode.AUTH_INVALID_ACCESS_TOKEN;
import static com.eodya.api.auth.exception.AuthExceptionCode.AUTH_INVALID_REGISTER_TOKEN;

@Component
@RequiredArgsConstructor
public class TokenExtractor {

    private static final String BEARER_TYPE = "Bearer ";

    public String extractRegisterToken(String header) {
        if (header != null && header.startsWith(BEARER_TYPE)) {
            return header.substring(BEARER_TYPE.length()).trim();
        }

        throw new AuthException(AUTH_INVALID_REGISTER_TOKEN, header);
    }

    public String extractAccessToken(String header) {
        if (header != null && header.startsWith(BEARER_TYPE)) {
            return header.substring(BEARER_TYPE.length()).trim();
        }

        throw new AuthException(AUTH_INVALID_ACCESS_TOKEN, header);
    }
}
