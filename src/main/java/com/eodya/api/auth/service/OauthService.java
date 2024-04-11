package com.eodya.api.auth.service;

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
import com.eodya.api.auth.service.authcode.AuthCodeRequestUrlProviderComposite;
import com.eodya.api.auth.service.memberClient.OauthMemberClientComposite;
import com.eodya.api.users.domain.User;
import com.eodya.api.users.dto.request.AuthenticatedUserResponse;
import com.eodya.api.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.eodya.api.auth.exception.AuthExceptionCode.AUTH_FAIL_TO_VALIDATE_TOKEN;
import static com.eodya.api.auth.exception.AuthExceptionCode.AUTH_INVALID_REFRESH_TOKEN;

@Service
@RequiredArgsConstructor
public class OauthService {

    private static final String REFRESH_TOKEN_KEY = "refresh_token";

    private final UserRepository userRepository;
    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final TokenExtractor tokenExtractor;
    private final JwtProvider jwtProvider;
    private final JwtProperties jwtProperties;
    private final RedisRepository redisRepository;

    public String getAuthCodeRequestUrl(OauthProvider oauthProvider) {
        return authCodeRequestUrlProviderComposite.provide(oauthProvider);
    }

    @Transactional
    public AuthenticatedUserResponse processLoginOrRegistration(
            OauthProvider oauthProvider,
            String authCode
    ) {
        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthProvider, authCode);
        Optional<User> member = userRepository.findByOauthId(oauthMember.getOauthId());

        if (member.isPresent()) {
            User loginMember = member.get();
            AuthTokens loginTokens = jwtProvider.createLoginToken(String.valueOf(loginMember.getId()));

            RefreshToken refreshToken = RefreshToken.builder()
                    .token(loginTokens.getRefreshToken())
                    .userId(loginMember.getId())
                    .createdAt(LocalDateTime.now())
                    .build();

            redisRepository.saveHash(
                    REFRESH_TOKEN_KEY,
                    refreshToken.getToken(),
                    refreshToken,
                    jwtProperties.getRefreshTokenExpirationTime()
            );

            return AuthenticatedUserResponse.of(loginMember, loginTokens);
        }

        String oauthProviderName = oauthMember.getOauthProvider().name();
        AuthTokens registerToken = jwtProvider.createRegisterToken(oauthProviderName + oauthMember.getOauthId());

        return AuthenticatedUserResponse.of(oauthMember, registerToken);
    }

    public AccessTokenResponse regenerateAccessToken(String refreshToken, String authorizationHeader) {
        String accessToken = tokenExtractor.extractAccessToken(authorizationHeader);

        if (jwtProvider.isValidRefreshAndInvalidAccess(refreshToken, accessToken)) {
            RefreshToken validRefreshToken = redisRepository.findHash(REFRESH_TOKEN_KEY,
                    refreshToken);

            if (validRefreshToken == null) {
                throw new AuthException(AUTH_INVALID_REFRESH_TOKEN);
            }

            String regeneratedAccessToken = jwtProvider.regenerateAccessToken(
                    validRefreshToken.getUserId().toString());

            return AccessTokenResponse.of(regeneratedAccessToken);
        }

        if (jwtProvider.isValidRefreshAndValidAccess(refreshToken, accessToken)) {
            return AccessTokenResponse.of(accessToken);
        }

        throw new AuthException(AUTH_FAIL_TO_VALIDATE_TOKEN);
    }

    public void deleteRefreshToken(String refreshToken) {
        redisRepository.deleteHash(REFRESH_TOKEN_KEY, refreshToken);
    }
}

