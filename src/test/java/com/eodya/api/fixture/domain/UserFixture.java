package com.eodya.api.fixture.domain;

import com.eodya.api.users.domain.OAuthProvider;
import com.eodya.api.users.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class UserFixture {

    public static User userBuild(String nickname, String OAuthId, OAuthProvider OAuthProvider) {
        return User.builder()
                .nickname(nickname)
                .OAuthId(OAuthId)
                .OAuthProvider(OAuthProvider)
                .build();
    }

    public static List<User> usersBuild(int count, OAuthProvider OAuthProvider) {
        List<User> users = new ArrayList<>();

        IntStream.range(0, count).forEach(i -> {
            String uniqueNickname = String.format("testUser%d", i);
            String uniqueOAuthId = String.format("OAuthId%d", i);

            users.add(
                    User.builder()
                            .nickname(uniqueNickname)
                            .OAuthId(uniqueOAuthId)
                            .OAuthProvider(OAuthProvider) // 모든 사용자에게 동일한 OAuthProvider 할당
                            .build()
            );
        });
        return users;
    }
}
