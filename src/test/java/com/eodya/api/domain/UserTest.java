package com.eodya.api.domain;

import com.eodya.api.users.domain.OAuthProvider;
import com.eodya.api.users.domain.User;
import com.eodya.api.fixture.domain.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.eodya.api.users.domain.OAuthProvider.KAKAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {

    @Test
    @DisplayName("정상적으로 유저를 생성할 수 있다.")
    void createUser_Success() {
        //given
        User user = UserFixture.userBuild();

        //when & then
        assertNotNull(user.getNickname());
        assertEquals(user.getNickname(), "testNickName");
    }

    @Test
    @DisplayName("새로운 유저를 생성하면, 유저의 북마크, 추천, 리뷰의 수는 0이다.")
    void createUser_bookmark_review_recommend_size_test() {
        //given
        User user = UserFixture.userBuild();

        //when & then
        assertEquals(user.getBookmarks().size(), 0);
        assertEquals(user.getRecommendations().size(), 0);
        assertEquals(user.getReviews().size(), 0);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("정상적으로 여러 명의 유저를 생성할 수 있다.")
    void createUsers_Success(int count) {
        //given
        List<User> users = UserFixture.usersBuild(count, OAuthProvider.KAKAO);

        //when & then
        users.forEach(user -> {
            assertNotNull(user.getNickname());
        });
    }
}
