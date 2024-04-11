package com.eodya.api.common.config.property;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Getter
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedisProperties {

    private final String host;
    private final Integer port;
    private final String password;
}
