package com.eodya.api.common.config.property;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {RedisProperties.class})
public class CommonConfig {

}
