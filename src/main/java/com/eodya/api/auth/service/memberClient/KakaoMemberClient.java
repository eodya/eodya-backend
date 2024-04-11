package com.eodya.api.auth.service.memberClient;

import com.eodya.api.auth.domain.oauth.OauthMember;
import com.eodya.api.auth.domain.oauth.OauthProvider;
import com.eodya.api.auth.domain.config.property.KakaoOauthProperties;
import com.eodya.api.auth.dto.kakao.KakaoTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class KakaoMemberClient implements OauthMemberClient{

    private final KakaoAuthApiClient kakaoAuthApiClient;
    private final KakaoMemberApiClient kakaoMemberApiClient;
    private final KakaoOauthProperties kakaoOauthProperties;

    @Override
    public OauthProvider oauthProvider() {
        return OauthProvider.KAKAO;
    }

    @Override
    public OauthMember fetch(final String authCode) {
        final KakaoTokenResponse tokenInfo = kakaoAuthApiClient.fetchToken(tokenRequestParams(authCode));

        return kakaoMemberApiClient.fetchMember("Bearer " + tokenInfo.getAccessToken()).toOauthMember();
    }

    private MultiValueMap<String, String> tokenRequestParams(final String authCode) {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoOauthProperties.getClientId());
        params.add("redirect_uri", kakaoOauthProperties.getRedirectUrl());
        params.add("code", authCode);
        params.add("client_secret", kakaoOauthProperties.getClientSecret());

        return params;
    }
}
