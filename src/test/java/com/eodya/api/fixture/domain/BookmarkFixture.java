package com.eodya.api.fixture.domain;

import com.eodya.api.bookmark.domain.Bookmark;
import com.eodya.api.bookmark.domain.BookmarkStatus;
import com.eodya.api.place.domain.Place;
import com.eodya.api.users.domain.User;

import java.util.ArrayList;
import java.util.List;

public class BookmarkFixture {

    public static Bookmark bookmarBuilder(
            User user,
            Place place,
            BookmarkStatus status
    ) {
        return Bookmark.builder()
                .user(user)
                .place(place)
                .status(status)
                .build();
    }

    public static List<Bookmark> bookmarkBuilder(
            List<User> users,
            Place place,
            BookmarkStatus status
    ) {
        List<Bookmark> bookmarks = new ArrayList<>();

        users.forEach(user -> {
            bookmarks.add(
                    Bookmark.builder()
                            .status(status)
                            .user(user)
                            .place(place)
                            .build()
            );
        });
        return bookmarks;
    }
}
