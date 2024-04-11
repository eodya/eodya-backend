package com.eodya.api.auth.service.memberClient;

import com.eodya.api.auth.dto.kakao.KakaoTokenResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.PostExchange;

public interface KakaoAuthApiClient {

    @PostExchange
    KakaoTokenResponse fetchToken(@RequestParam MultiValueMap<String, String> params);
}
