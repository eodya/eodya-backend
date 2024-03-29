package com.eodya.api.fixture.domain;

import com.eodya.api.users.domain.OAuthProvider;
import com.eodya.api.users.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UserFixture {

    public static User userBuild() {
        return User.builder()
                .nickname("testNickName")
                .OAuthId("testOAuthId")
                .OAuthProvider(OAuthProvider.KAKAO)
                .build();
    }

    public static List<User> usersBuild(int count, OAuthProvider OAuthProvider) {
        return IntStream.range(0, count)
                .mapToObj(i -> User.builder()
                        .nickname(String.format("testUser%d", i))
                        .OAuthId(String.format("OAuthId%d", i))
                        .OAuthProvider(OAuthProvider)
                        .build())
                .collect(Collectors.toList());
    }
}
