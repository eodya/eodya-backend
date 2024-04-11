package com.eodya.api.auth.service.memberClient;

import com.eodya.api.auth.dto.kakao.KakaoMemberResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public interface KakaoMemberApiClient {

    @GetExchange
    KakaoMemberResponse fetchMember(@RequestHeader(name = AUTHORIZATION) String bearerToken);
}
