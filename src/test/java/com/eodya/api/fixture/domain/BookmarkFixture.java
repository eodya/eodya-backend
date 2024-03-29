package com.eodya.api.fixture.domain;

import com.eodya.api.bookmark.domain.Bookmark;
import com.eodya.api.bookmark.domain.BookmarkStatus;
import com.eodya.api.place.domain.Place;
import com.eodya.api.users.domain.OAuthProvider;
import com.eodya.api.users.domain.User;

import java.util.List;
import java.util.stream.IntStream;

public class BookmarkFixture {

    public static Bookmark bookmarkBuilder() {

        User testuser = UserFixture.userBuild();
        Place testPlace = PlaceFixture.placeBuild();

        return Bookmark.builder()
                .user(testuser)
                .place(testPlace)
                .status(BookmarkStatus.TRUE)
                .build();
    }

    public static List<Bookmark> bookmarksBuilder(int count) {

        List<User> users = UserFixture.usersBuild(count, OAuthProvider.KAKAO);
        List<Place> places = PlaceFixture.placesBuild(count);
        List<Bookmark> bookmarks = IntStream.range(0, count)
                .mapToObj(i -> {
                    User user = users.get(i % users.size());
                    Place place = places.get(i % places.size());
                    return Bookmark.builder()
                            .status(BookmarkStatus.TRUE)
                            .user(user)
                            .place(place)
                            .build();
                })
                .toList();

        return bookmarks;
    }
}