package com.eodya.api.auth.domain.oauth;

public enum OauthProvider {

    KAKAO,
    UNKNOWN,
    ;

    public static OauthProvider from(String type) {
        return OauthProvider.valueOf(type.toUpperCase());
    }
}
