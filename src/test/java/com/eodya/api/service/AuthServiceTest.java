package com.eodya.api.service;

import com.eodya.api.auth.domain.config.property.JwtProperties;
import com.eodya.api.auth.domain.config.resolver.TokenExtractor;
import com.eodya.api.auth.domain.oauth.OauthMember;
import com.eodya.api.auth.domain.oauth.OauthProvider;
import com.eodya.api.auth.domain.token.AuthTokens;
import com.eodya.api.auth.domain.token.JwtProvider;
import com.eodya.api.auth.domain.token.RefreshToken;
import com.eodya.api.auth.dto.response.AccessTokenResponse;
import com.eodya.api.auth.exception.AuthException;
import com.eodya.api.auth.repository.RedisRepository;
import com.eodya.api.auth.service.OauthService;
import com.eodya.api.auth.service.authcode.AuthCodeRequestUrlProviderComposite;
import com.eodya.api.auth.service.memberClient.OauthMemberClientComposite;
import com.eodya.api.fixture.domain.AuthFixture;
import com.eodya.api.fixture.domain.UserFixture;
import com.eodya.api.users.domain.User;
import com.eodya.api.users.dto.request.AuthenticatedUserResponse;
import com.eodya.api.users.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.eodya.api.auth.exception.AuthExceptionCode.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    private static final OauthProvider OAUTH_PROVIDER = OauthProvider.KAKAO;
    private static final String REFRESH_TOKEN_KEY = "refresh_token";

    @InjectMocks
    private OauthService oauthService;

    @Mock
    private AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;

    @Mock
    private OauthMemberClientComposite oauthMemberClientComposite;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private RedisRepository redisRepository;

    @Mock
    private JwtProperties jwtProperties;

    @Mock
    private TokenExtractor tokenExtractor;

    @Test
    @DisplayName("정상적으로 Oauth 제공자를 통해 Oauth 로그인 페이지로 리다이렉션한다.")
    void oauthLoginRedirect_Success() {
        // given
        String redirectUrl = "http://test.url";

        given(authCodeRequestUrlProviderComposite.provide(OAUTH_PROVIDER)).willReturn(redirectUrl);

        // when
        String oauthRedirectUrl = oauthService.getAuthCodeRequestUrl(OAUTH_PROVIDER);

        // then
        assertThat(oauthRedirectUrl).isEqualTo(redirectUrl);
    }

    @Test
    @DisplayName("지원하지 않는 소셜 로그인 타입일 경우, 예외가 발생한다.")
    void oauthProvider_NotSupported_Fail() {
        //given
        OauthProvider unsupportedProvider = OauthProvider.UNKNOWN;

        given(authCodeRequestUrlProviderComposite.provide(unsupportedProvider))
                .willThrow(new AuthException(AUTH_NOT_FOUND_OAUTH_PROVIDER));

        //when & then
        assertThatThrownBy(() -> oauthService.getAuthCodeRequestUrl(unsupportedProvider))
                .isInstanceOf(AuthException.class)
                .hasMessageContaining(AUTH_NOT_FOUND_OAUTH_PROVIDER.getMessage());
    }

    @Test
    @DisplayName("정상적으로 Oauth를 이용하여 로그인을 할 수 있다.")
    void oauthLogin_Success() {
        //given
        String authCode = "authCode";
        OauthMember oauthMember = AuthFixture.authBuilder();
        User user = UserFixture.userBuild();
        AuthTokens loginTokens = AuthFixture.authTokensBuild();
        Long refreshTokenExpirationTime = 10000L;

        given(userRepository.findByOauthId(anyLong())).willReturn(Optional.ofNullable(user));
        given(oauthMemberClientComposite.fetch(any(OauthProvider.class), anyString())).willReturn(oauthMember);
        given(jwtProvider.createLoginToken(anyString())).willReturn(loginTokens);
        given(jwtProperties.getRefreshTokenExpirationTime()).willReturn(refreshTokenExpirationTime);

        //when
        AuthenticatedUserResponse authenticatedUserResponse = oauthService.processLoginOrRegistration(OAUTH_PROVIDER, authCode);

        //then
        assertThat(authenticatedUserResponse.getOauthId()).isEqualTo(user.getOauthId());
        assertThat(authenticatedUserResponse.getOauthProvider()).isEqualTo(user.getOauthProvider());
        assertThat(authenticatedUserResponse.getNickname()).isEqualTo(user.getNickname());
        assertThat(authenticatedUserResponse.getAccessToken()).isEqualTo(loginTokens.getAccessToken());
        assertThat(authenticatedUserResponse.getRefreshToken()).isEqualTo(loginTokens.getRefreshToken());

        verify(userRepository).findByOauthId(anyLong());
        verify(oauthMemberClientComposite).fetch(any(OauthProvider.class), anyString());
        verify(jwtProvider).createLoginToken(anyString());
        verify(jwtProperties).getRefreshTokenExpirationTime();
    }

    @Test
    @DisplayName("Oauth를 이용하여 로그인시 회원 정보가 존재하지 않으면 회원가입 관련 정보를 응답한다. ")
    void oauthLogin_NotUser_SignUpInfo_Success() {
        // given
        String authCode = "authCode";
        OauthMember oauthMember = AuthFixture.authBuilder();
        User user = null;
        AuthTokens registerToken = AuthFixture.authTokensBuild();

        given(userRepository.findByOauthId(anyLong())).willReturn(Optional.ofNullable(user));
        given(oauthMemberClientComposite.fetch(any(OauthProvider.class), anyString())).willReturn(oauthMember);
        given(jwtProvider.createRegisterToken(anyString())).willReturn(registerToken);

        //when
        AuthenticatedUserResponse authenticatedUserResponse = oauthService.processLoginOrRegistration(OAUTH_PROVIDER, authCode);

        //then
        assertThat(authenticatedUserResponse.getOauthId()).isEqualTo(oauthMember.getOauthId());
        assertThat(authenticatedUserResponse.getOauthProvider()).isEqualTo(oauthMember.getOauthProvider());
        assertThat(authenticatedUserResponse.getNickname()).isEqualTo(oauthMember.getNickname());
        assertThat(authenticatedUserResponse.getAccessToken()).isEqualTo(registerToken.getAccessToken());
        assertThat(authenticatedUserResponse.getId()).isNull();
        assertThat(authenticatedUserResponse.getRefreshToken()).isNull();

        verify(userRepository).findByOauthId(anyLong());
        verify(oauthMemberClientComposite).fetch(any(OauthProvider.class), anyString());
        verify(jwtProvider).createRegisterToken(anyString());
    }

    @Test
    @DisplayName("정상적으로 accessToken이 만료되었을 때, 새로 갱신할 수 있다.")
    void accessTokenRefresh_Success() {
        // given
        AuthTokens authTokens = AuthFixture.authTokensBuild();
        String authorizationHeader = "Bearer " + authTokens.getAccessToken();
        RefreshToken refreshToken = AuthFixture.refreshTokenBuild();
        String regeneratedAccessToken = AuthFixture.authTokensBuild().getAccessToken();

        given(tokenExtractor.extractAccessToken(authorizationHeader)).willReturn(authTokens.getAccessToken());
        given(jwtProvider.isValidRefreshAndInvalidAccess(refreshToken.getToken(), authTokens.getAccessToken())).willReturn(true);
        given(redisRepository.findHash(REFRESH_TOKEN_KEY, refreshToken.getToken())).willReturn(refreshToken);
        given(jwtProvider.regenerateAccessToken(refreshToken.getUserId().toString())).willReturn(regeneratedAccessToken);

        // when
        AccessTokenResponse accessTokenResponse = oauthService.regenerateAccessToken(refreshToken.getToken(), authorizationHeader);

        //then
        assertThat(accessTokenResponse.getAccessToken()).isEqualTo(regeneratedAccessToken);

        verify(tokenExtractor).extractAccessToken(authorizationHeader);
        verify(jwtProvider).isValidRefreshAndInvalidAccess(refreshToken.getToken(), authTokens.getAccessToken());
        verify(redisRepository).findHash(REFRESH_TOKEN_KEY, refreshToken.getToken());
        verify(jwtProvider).regenerateAccessToken(refreshToken.getUserId().toString());
    }

    @Test
    @DisplayName("RefreshToken이 null인경우, 예외가 발생한다.")
    void refreshToken_IsNULL_Fail() {
        // given
        AuthTokens authTokens = AuthFixture.authTokensBuild();
        String authorizationHeader = "Bearer" + authTokens.getAccessToken();
        String refreshToken = null;

        given(tokenExtractor.extractAccessToken(authorizationHeader)).willReturn("accessToken");

        // when & then
        assertThatThrownBy(() -> oauthService.regenerateAccessToken(refreshToken, authorizationHeader))
                .isInstanceOf(AuthException.class)
                .hasMessageContaining(AUTH_FAIL_TO_VALIDATE_TOKEN.getMessage());
    }

    @Test
    @DisplayName("AccessToken 토큰 만료로 인한 예외가 발생한다.")
    void accessToken_Expired_Fail() {
        // given
        AuthTokens authTokens = AuthFixture.authTokensBuild();
        String authorizationHeader = "Bearer" + authTokens.getAccessToken();
        String refreshToken = null;

        given(tokenExtractor.extractAccessToken(authorizationHeader)).willReturn("accessToken");

        //when& then
        assertThatThrownBy(() -> oauthService.regenerateAccessToken(refreshToken, authorizationHeader))
                .isInstanceOf(AuthException.class)
                .hasMessageContaining(AUTH_FAIL_TO_VALIDATE_TOKEN.getMessage());
    }

    @Test
    @DisplayName("RefreshToken 토큰 만료로 인한 예외가 발생한다.")
    void refreshToken_Expired_Fail() {
        //given
        String accessToken = "accessToken";
        String refreshToken = "refreshToken";
        String authorizationHeader = "Bearer " + accessToken;

        given(tokenExtractor.extractAccessToken(authorizationHeader)).willReturn(accessToken);
        given(jwtProvider.isValidRefreshAndInvalidAccess(refreshToken, accessToken)).willReturn(false);

        //when & then
        assertThatThrownBy(() -> oauthService.regenerateAccessToken(refreshToken, authorizationHeader))
                .isInstanceOf(AuthException.class)
                .hasMessageContaining(AUTH_FAIL_TO_VALIDATE_TOKEN.getMessage());
    }

    @Test
    @DisplayName("유효하지 않은 AccessToken으로 인한 예외가 발생한다.")
    void accessToken_Invalid_Fail() {
        //given
        String accessToken = "accessToken";
        String refreshToken = "refreshToken";
        String authorizationHeader = "Bearer " + accessToken;

        given(tokenExtractor.extractAccessToken(authorizationHeader)).willReturn(accessToken);
        given(jwtProvider.isValidRefreshAndInvalidAccess(refreshToken, accessToken)).willReturn(true);

        //when & then
        assertThatThrownBy(() -> oauthService.regenerateAccessToken(refreshToken, authorizationHeader))
                .isInstanceOf(AuthException.class)
                .hasMessageContaining(AUTH_INVALID_REFRESH_TOKEN.getMessage());
    }

    @Test
    @DisplayName("유효하지 않은 RefreshToken으로 인한 예외가 발생한다.")
    void refreshToken_Invalid_Fail() {
        //given
        String accessToken = "accessToken";
        String refreshToken = "refreshToken";
        String authorizationHeader = "Bearer " + accessToken;

        given(tokenExtractor.extractAccessToken(authorizationHeader)).willReturn(accessToken);
        given(jwtProvider.isValidRefreshAndInvalidAccess(refreshToken, accessToken)).willReturn(true);

        //when & then
        assertThatThrownBy(() -> oauthService.regenerateAccessToken(refreshToken, authorizationHeader))
                .isInstanceOf(AuthException.class)
                .hasMessageContaining(AUTH_INVALID_REFRESH_TOKEN.getMessage());
    }

    @Test
    @DisplayName("RefreshToken이 존재하지 않음으로 인한 예외가 발생한다.")
    void refreshToken_NotFound_Fail() {
        //given
        String accessToken = "accessToken";
        String refreshToken = "refreshToken";
        String authorizationHeader = "Bearer " + accessToken;

        given(tokenExtractor.extractAccessToken(authorizationHeader)).willReturn(accessToken);

        //when & then
        assertThatThrownBy(() -> oauthService.regenerateAccessToken(refreshToken, authorizationHeader))
                .isInstanceOf(AuthException.class)
                .hasMessageContaining(AUTH_FAIL_TO_VALIDATE_TOKEN.getMessage());
    }

    @Test
    @DisplayName("토큰을 검증할 수 없어 예외가 발생한다.")
    void token_Validation_Fail() {
        //given
        String accessToken = "accessToken";
        String refreshToken = "refreshToken";
        String authorizationHeader = "Bearer " + accessToken;

        given(tokenExtractor.extractAccessToken(authorizationHeader)).willReturn(accessToken);
        given(jwtProvider.isValidRefreshAndInvalidAccess(refreshToken, accessToken)).willReturn(false);
        given(jwtProvider.isValidRefreshAndValidAccess(refreshToken, accessToken)).willReturn(false);

        //when & then
        assertThatThrownBy(() -> oauthService.regenerateAccessToken(refreshToken, authorizationHeader))
                .isInstanceOf(AuthException.class)
                .hasMessageContaining(AUTH_FAIL_TO_VALIDATE_TOKEN.getMessage());
    }
}
