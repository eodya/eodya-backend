package com.eodya.api.auth.dto.kakao;

import com.eodya.api.auth.domain.oauth.OauthMember;
import com.eodya.api.auth.domain.oauth.OauthProvider;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoMemberResponse {

    private Long id;
    private KaKaoAccount kaKaoAccount;

    public OauthMember toOauthMember() {
        return OauthMember.builder()
                .oauthId(id)
                .nickname(kaKaoAccount.profile.nickname)
                .oauthProvider(OauthProvider.KAKAO)
                .build();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class KaKaoAccount {

        private Profile profile;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class Profile {

        private String nickname;
    }
}
