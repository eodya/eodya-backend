package com.eodya.api.auth.domain.config.http;

import com.eodya.api.auth.domain.config.WebClientConfig;
import com.eodya.api.auth.domain.config.property.KakaoOauthProperties;
import com.eodya.api.auth.service.memberClient.KakaoAuthApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@Configuration
@RequiredArgsConstructor
public class KakaoAuthHttpInterfaceConfig {

    private final KakaoOauthProperties kakaoOauthProperties;

    @Bean
    public KakaoAuthApiClient kakaoAuthApiClient() {
        return createHttpInterface(KakaoAuthApiClient.class);
    }

    private <T> T createHttpInterface(Class<T> clazz) {
        WebClient webClient = WebClient.builder()
                .defaultHeader(CONTENT_TYPE, APPLICATION_FORM_URLENCODED_VALUE)
                .baseUrl(kakaoOauthProperties.getAuthUrl())
                .exchangeStrategies(WebClientConfig.getExchangeStrategies())
                .build();

        HttpServiceProxyFactory build = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();

        return build.createClient(clazz);
    }
}
