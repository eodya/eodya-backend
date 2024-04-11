package com.eodya.api.auth.domain.config;

import com.eodya.api.auth.domain.config.property.CorsProperties;
import com.eodya.api.auth.domain.config.property.JwtProperties;
import com.eodya.api.auth.domain.config.property.KakaoOauthProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {KakaoOauthProperties.class, CorsProperties.class, JwtProperties.class})
public class AuthConfig {

}
