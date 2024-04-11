package com.eodya.api.auth.domain.config.property;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secretKey;
    private Long accessTokenExpirationTime;
    private Long refreshTokenExpirationTime;
    private Long registerTokenExpirationTime;
}
