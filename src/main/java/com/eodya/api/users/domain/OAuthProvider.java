package com.eodya.api.users.domain;

import com.eodya.api.users.exception.AuthException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.eodya.api.users.exception.AuthExceptionCode.OAUTH_PROVIDER_NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum OAuthProvider {

    KAKAO("카카오"),
    ;

    @JsonValue
    private final String description;

    @JsonCreator
    public static OAuthProvider from(String description) throws AuthException {
        if (statusMap.containsKey(description)) {
            return statusMap.get(description);
        }
        throw new AuthException(OAUTH_PROVIDER_NOT_FOUND, description);
    }

    private static final Map<String, OAuthProvider> statusMap =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(OAuthProvider::getDescription, Function.identity())));
}
