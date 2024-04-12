package com.eodya.api.auth.service.authcode;

import com.eodya.api.auth.domain.config.property.KakaoOauthProperties;
import com.eodya.api.auth.domain.oauth.OauthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class KakaoAuthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider{

    private final KakaoOauthProperties kakaoOauthProperties;

    @Override
    public OauthProvider oauthprovider() {
        return OauthProvider.KAKAO;
    }

    @Override
    public String provideUrl() {
        return UriComponentsBuilder
                .fromUriString(kakaoOauthProperties.getProviderUrl())
                .queryParam("response_type", "code")
                .queryParam("client_id", kakaoOauthProperties.getClientId())
                .queryParam("redirect_uri", kakaoOauthProperties.getRedirectUrl())
                .toUriString();
    }
}
