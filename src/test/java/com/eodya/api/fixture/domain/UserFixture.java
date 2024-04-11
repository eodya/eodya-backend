package com.eodya.api.fixture.domain;

import com.eodya.api.auth.domain.oauth.OauthProvider;
import com.eodya.api.users.domain.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UserFixture {

    public static User userBuild() {
        return User.builder()
                .nickname("테스트 유저 닉네임")
                .oauthId(1L)
                .oauthProvider(OauthProvider.KAKAO)
                .build();
    }

    public static List<User> usersBuild(int count, OauthProvider OAuthProvider) {
        return IntStream.range(0, count)
                .mapToObj(i -> User.builder()
                        .nickname(String.format("테스트 유저 닉네임 %d", i))
                        .oauthId(i + 1L)
                        .oauthProvider(OAuthProvider)
                        .build())
                .collect(Collectors.toList());
    }
}
