package com.eodya.api.domain;

import com.eodya.api.users.domain.User;
import com.eodya.api.fixture.domain.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {

    @Test
    @DisplayName("정상적으로 유저를 생성할 수 있다.")
    void createUser_Success() {
        //given
        String userName = "재훈";
        String OAuthId = "123456";
        String OAuthProvider = "KAKAO";
        User user = UserFixture.userBuild(userName, OAuthId, OAuthProvider);

        //when & then
        assertNotNull(user.getNickname());
        assertEquals(user.getNickname(), userName);
    }

    @Test
    @DisplayName("새로운 유저를 생성하면, 유저의 북마크, 추천, 리뷰의 수는 0이다.")
    void createUser_bookmark_review_recommend_size_test() {
        //given
        String userName = "재훈";
        String OAuthId = "123456";
        String OAuthProvider = "KAKAO";
        User user = UserFixture.userBuild(userName, OAuthId, OAuthProvider);

        //when & then
        assertEquals(user.getBookMarks().size(), 0);
        assertEquals(user.getRecommendations().size(), 0);
        assertEquals(user.getRecommendations().size(), 0);
    }
}
