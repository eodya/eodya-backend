package com.eodya.api.domain;

import com.eodya.api.auth.domain.oauth.OauthMember;
import com.eodya.api.auth.domain.oauth.OauthProvider;
import com.eodya.api.auth.domain.token.AuthTokens;
import com.eodya.api.auth.domain.token.RefreshToken;
import com.eodya.api.fixture.domain.AuthFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class AuthTest {

    @Test
    @DisplayName("정상적으로 OAuth객체가 생성된다.")
    void authenticateWithOAuth_Success() {
        //given
        OauthMember member = AuthFixture.authBuilder();

        //when & then
        assertThat(member.getOauthId()).isEqualTo(1L);
        assertThat(member.getOauthProvider()).isEqualTo(OauthProvider.KAKAO);
        assertThat(member.getNickname()).isEqualTo("테스트 닉네임");
    }


    @Test
    @DisplayName("정상적으로 OAuth를 이용하여 토큰을 발급받을 수 있다.")
    void authenticateWithToken_Success() {
        //given
        AuthTokens tokens = AuthFixture.authTokensBuild();

        //when & then
        assertThat(tokens.getAccessToken()).isEqualTo("accessToken");
        assertThat(tokens.getRefreshToken()).isEqualTo("refreshToken");
    }

    @Test
    @DisplayName("정상적으로 리프레시된 토큰이 생성된다.")
    void refreshToken_Success() {
        //given
        RefreshToken refreshToken = AuthFixture.refreshTokenBuild();

        //when & then
        assertThat(refreshToken.getToken()).isEqualTo("refreshToken");
        assertThat(refreshToken.getUserId()).isEqualTo(1L);
        assertThat(refreshToken.getCreatedAt()).isBeforeOrEqualTo(LocalDateTime.now());
    }
}
