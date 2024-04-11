package com.eodya.api.auth.domain.oauth;

public enum OauthProvider {

    KAKAO,
    ;

    public static OauthProvider from(String type) {
        return OauthProvider.valueOf(type.toUpperCase());
    }
}
