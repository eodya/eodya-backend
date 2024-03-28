package com.eodya.api.external.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.eodya.api.external.login.kakao"})
public class OpenFeignConfig {

}