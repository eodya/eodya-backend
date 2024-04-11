package com.eodya.api.auth.domain.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebClientConfig {

    public static ExchangeStrategies getExchangeStrategies() {
        ObjectMapper objectMapper = new ObjectMapper()
                .setPropertyNamingStrategy(SNAKE_CASE)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs()
                        .jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper, APPLICATION_JSON)))
                .build();
    }
}
