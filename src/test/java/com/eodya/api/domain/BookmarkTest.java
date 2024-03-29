package com.eodya.api.domain;

import com.eodya.api.address.domain.AddressDepth1;
import com.eodya.api.address.domain.AddressDepth2;
import com.eodya.api.bookmark.domain.Bookmark;
import com.eodya.api.bookmark.domain.BookmarkStatus;
import com.eodya.api.fixture.domain.BookmarkFixture;
import com.eodya.api.fixture.domain.PlaceFixture;
import com.eodya.api.fixture.domain.UserFixture;
import com.eodya.api.place.domain.Place;
import com.eodya.api.users.domain.OAuthProvider;
import com.eodya.api.users.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BookmarkTest {

    @Test
    @DisplayName("정상적으로 북마크를 생성할 수 있다.")
    void createBookmark_Success() {
        //given
        User testuser = UserFixture.userBuild("test", "testOAuthId", OAuthProvider.KAKAO);
        Point testPoint = PlaceFixture.pointBuild(37.5, 37.5);

        AddressDepth1 testAddressDepth1 = AddressDepth1.builder().name("testCity").build();
        AddressDepth2 testAddressDepth2 = AddressDepth2.builder().name("testGu").addressDepth1(testAddressDepth1).build();

        Place testPlace = PlaceFixture.placeBuild(testPoint, "testPlace", "testAddressDetail", testuser, testAddressDepth1, testAddressDepth2);
        Bookmark bookmark = BookmarkFixture.bookmarkBuilder(testuser, testPlace, BookmarkStatus.TRUE);

        //when & then
        assertNotNull(bookmark.getUser());
        assertNotNull(bookmark.getPlace());
        assertEquals(testuser, bookmark.getUser());
        assertEquals(testPlace, bookmark.getPlace());
        assertEquals(BookmarkStatus.TRUE, bookmark.getStatus());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("정상적으로 한 장소에 여러 명의 유저가 북마크를 생성할 수 있다.")
    void createBookmarks_Success(int count) {
        //given
        List<User> users = UserFixture.usersBuild(count, OAuthProvider.KAKAO);
        Point testPoint = PlaceFixture.pointBuild(37.5, 37.5);
        AddressDepth1 testAddressDepth1 = AddressDepth1.builder().name("testCity").build();
        AddressDepth2 testAddressDepth2 = AddressDepth2.builder().name("testGu").addressDepth1(testAddressDepth1).build();

        Place testPlace = PlaceFixture.placeBuild(testPoint, "testPlace", "testAddressDetail", users.get(0), testAddressDepth1, testAddressDepth2);

        List<Bookmark> bookmarks = new ArrayList<>();
        for (User user : users) {
            bookmarks.add(BookmarkFixture.bookmarkBuilder(user, testPlace, BookmarkStatus.TRUE));
        }

        //when & then
        bookmarks.forEach(bookmark -> {
            assertNotNull(bookmark.getUser());
            assertNotNull(bookmark.getPlace());
            assertEquals(BookmarkStatus.TRUE, bookmark.getStatus());
        });
    }

    @Test
    @DisplayName("북마크를 해제할 수 있다.")
    void removeBookmark_Success() {
        //given
        User testuser = UserFixture.userBuild("test", "testOAuthId", OAuthProvider.KAKAO);
        Point testPoint = PlaceFixture.pointBuild(37.5, 37.5);

        AddressDepth1 testAddressDepth1 = AddressDepth1.builder().name("testCity").build();
        AddressDepth2 testAddressDepth2 = AddressDepth2.builder().name("testGu").addressDepth1(testAddressDepth1).build();

        Place testPlace = PlaceFixture.placeBuild(testPoint, "testPlace", "testAddressDetail", testuser, testAddressDepth1, testAddressDepth2);
        Bookmark bookmark = BookmarkFixture.bookmarkBuilder(testuser, testPlace, BookmarkStatus.FALSE);

        //when & then
        assertNotNull(bookmark.getUser());
        assertNotNull(bookmark.getPlace());
        assertEquals(testuser, bookmark.getUser());
        assertEquals(testPlace, bookmark.getPlace());
        assertEquals(BookmarkStatus.FALSE, bookmark.getStatus());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("정상적으로 한 장소에 여러 명의 유저가 북마크를 취소 할 수 있다.")
    void removeBookmarks_Success(int count) {
        //given
        List<User> users = UserFixture.usersBuild(count, OAuthProvider.KAKAO);
        Point testPoint = PlaceFixture.pointBuild(37.5, 37.5);
        AddressDepth1 testAddressDepth1 = AddressDepth1.builder().name("testCity").build();
        AddressDepth2 testAddressDepth2 = AddressDepth2.builder().name("testGu").addressDepth1(testAddressDepth1).build();

        Place testPlace = PlaceFixture.placeBuild(testPoint, "testPlace", "testAddressDetail", users.get(0), testAddressDepth1, testAddressDepth2);

        List<Bookmark> bookmarks = new ArrayList<>();
        for (User user : users) {
            bookmarks.add(BookmarkFixture.bookmarkBuilder(user, testPlace, BookmarkStatus.FALSE));
        }

        //when & then
        bookmarks.forEach(bookmark -> {
            assertNotNull(bookmark.getUser());
            assertNotNull(bookmark.getPlace());
            assertEquals(BookmarkStatus.FALSE, bookmark.getStatus());
        });
    }
}
