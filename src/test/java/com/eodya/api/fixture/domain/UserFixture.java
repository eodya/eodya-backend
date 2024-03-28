package com.eodya.api.fixture.domain;

import com.eodya.api.users.domain.OAuthProvider;
import com.eodya.api.users.domain.User;

public class UserFixture {

    public static User userBuild(String nickname, String OAuthId, OAuthProvider OAuthProvider) {
        return User.builder()
                .nickname(nickname)
                .OAuthId(OAuthId)
                .OAuthProvider(OAuthProvider)
                .build();
    }
}
