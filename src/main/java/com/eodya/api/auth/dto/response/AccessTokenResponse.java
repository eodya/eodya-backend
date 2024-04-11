package com.eodya.api.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class AccessTokenResponse {

    private String accessToken;
}
