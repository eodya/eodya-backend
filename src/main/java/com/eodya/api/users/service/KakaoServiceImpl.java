package com.eodya.api.users.service;

import static com.eodya.api.users.exception.AuthExceptionCode.INVALID_KAKAO_TOKEN;

import com.eodya.api.external.login.kakao.KakaoApiClient;
import com.eodya.api.external.login.kakao.dto.response.KakaoUserResponse;
import com.eodya.api.users.exception.OAuthException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoServiceImpl implements SocialService{

    private final KakaoApiClient kakaoApiClient;

    @Override
    public String getOAuthId(String token) {
        try{
            KakaoUserResponse userId = kakaoApiClient.getUserInformation("Bearer " + token);
            return String.valueOf(userId.getId());
        } catch (FeignException e) {
            throw new OAuthException(INVALID_KAKAO_TOKEN);
        }
    }

}
