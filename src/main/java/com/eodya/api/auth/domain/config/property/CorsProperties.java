package com.eodya.api.auth.domain.config.property;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Getter
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {

    private String[] urls;
}
