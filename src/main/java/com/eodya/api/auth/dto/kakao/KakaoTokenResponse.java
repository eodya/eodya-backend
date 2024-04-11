package com.eodya.api.auth.dto.kakao;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(staticName = "of")
public class KakaoTokenResponse {

    private String refreshToken;
    private String tokenType;
    private String accessToken;
    private String expiresIn;
}
