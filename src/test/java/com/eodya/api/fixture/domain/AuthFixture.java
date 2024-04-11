package com.eodya.api.fixture.domain;

import com.eodya.api.auth.domain.oauth.OauthMember;
import com.eodya.api.auth.domain.oauth.OauthProvider;
import com.eodya.api.auth.domain.token.AuthTokens;
import com.eodya.api.auth.domain.token.RefreshToken;

import java.time.LocalDateTime;

public class AuthFixture {

    public static OauthMember authBuilder() {
        return OauthMember.builder()
                .oauthId(1L)
                .oauthProvider(OauthProvider.KAKAO)
                .nickname("테스트 닉네임")
                .build();
    }
    public static AuthTokens authTokensBuild() {
        return AuthTokens.builder()
                .accessToken("accessToken")
                .refreshToken("refreshToken")
                .build();
    }

    public static RefreshToken refreshTokenBuild() {
        return RefreshToken.builder()
                .token("refreshToken")
                .userId(1L)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
