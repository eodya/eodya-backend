package com.eodya.api.auth.domain.config.property;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Getter
@ConfigurationProperties(prefix = "oauth.kakao")
public class KakaoOauthProperties {

    private String clientId;
    private String clientSecret;
    private String authUrl;
    private String redirectUrl;
    private String userUrl;
    private String providerUrl;
}
