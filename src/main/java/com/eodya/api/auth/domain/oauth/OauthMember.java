package com.eodya.api.auth.domain.oauth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OauthMember {

    private Long oauthId;
    private String nickname;
    private OauthProvider oauthProvider;
}
