package com.eodya.api.users.dto.request;

import com.eodya.api.auth.domain.oauth.OauthMember;
import com.eodya.api.auth.domain.oauth.OauthProvider;
import com.eodya.api.auth.domain.token.AuthTokens;
import com.eodya.api.users.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthenticatedUserResponse {

    private String accessToken;
    private String refreshToken;
    private Long id;
    private String nickname;
    private Long oauthId;
    private OauthProvider oauthProvider;

    @Builder
    private AuthenticatedUserResponse(
            String accessToken,
            String refreshToken,
            Long id,
            String nickname,
            Long oauthId,
            OauthProvider oauthProvider
    ) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.nickname = nickname;
        this.oauthId = oauthId;
        this.oauthProvider = oauthProvider;
    }

    public static AuthenticatedUserResponse of(User user, AuthTokens authTokens) {
        return AuthenticatedUserResponse.builder()
                .accessToken(authTokens.getAccessToken())
                .refreshToken(authTokens.getRefreshToken())
                .id(user.getId())
                .nickname(user.getNickname())
                .oauthId(user.getOauthId())
                .oauthProvider(user.getOauthProvider())
                .build();
    }

    public static AuthenticatedUserResponse of(OauthMember oauthMember, AuthTokens registerToken) {
        return AuthenticatedUserResponse.builder()
                .accessToken(registerToken.getAccessToken())
                .nickname(oauthMember.getNickname())
                .oauthId(oauthMember.getOauthId())
                .oauthProvider(oauthMember.getOauthProvider())
                .build();
    }


}
