package com.eodya.api.fixture.domain;

import com.eodya.api.entity.User;

public class UserFixture {

    public static User userBuild(String nickname, String OAuthId, String OAuthProvider) {
        return User.builder()
                .nickname(nickname)
                .OAuthId(OAuthId)
                .OAuthProvider(OAuthProvider)
                .build();
    }
}
