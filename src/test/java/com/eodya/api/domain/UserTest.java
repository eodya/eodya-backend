package com.eodya.api.domain;

import com.eodya.api.auth.domain.oauth.OauthProvider;
import com.eodya.api.users.domain.User;
import com.eodya.api.fixture.domain.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.eodya.api.auth.domain.oauth.OauthProvider.KAKAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserTest {

    @Test
    @DisplayName("정상적으로 유저를 생성할 수 있다.")
    void createUser_Success() {
        //given
        User user = UserFixture.userBuild();

        //when & then
        assertNotNull(user.getNickname());
        assertEquals(user.getNickname(), "테스트 유저 닉네임");
        assertEquals(user.getOauthId(), 1L);
        assertEquals(user.getOauthProvider(), KAKAO);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("정상적으로 여러 명의 유저를 생성할 수 있다.")
    void createUsers_Success(int count) {
        //given
        List<User> users = UserFixture.usersBuild(count, OauthProvider.KAKAO);

        //when & then
        users.forEach(user -> {
            assertNotNull(user.getNickname());
            assertNotNull(user.getOauthId());
            assertNotNull(user.getOauthProvider());
        });
    }
}
