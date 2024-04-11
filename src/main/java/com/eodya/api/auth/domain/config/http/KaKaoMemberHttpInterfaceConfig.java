package com.eodya.api.auth.domain.config.http;

import com.eodya.api.auth.domain.config.WebClientConfig;
import com.eodya.api.auth.domain.config.property.KakaoOauthProperties;
import com.eodya.api.auth.service.memberClient.KakaoMemberApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class KaKaoMemberHttpInterfaceConfig {

    private final KakaoOauthProperties kakaoOauthProperties;

    @Bean
    public KakaoMemberApiClient kakaoMemberApiClient() {
        return createHttpInterface(KakaoMemberApiClient.class);
    }

    private <T> T createHttpInterface(Class<T> clazz) {
        WebClient webClient = WebClient.builder()
                .baseUrl(kakaoOauthProperties.getUserUrl())
                .exchangeStrategies(WebClientConfig.getExchangeStrategies())
                .build();

        HttpServiceProxyFactory build = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();

        return build.createClient(clazz);
    }
}
