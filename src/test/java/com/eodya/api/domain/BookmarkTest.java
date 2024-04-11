package com.eodya.api.domain;

import com.eodya.api.bookmark.domain.Bookmark;
import com.eodya.api.fixture.domain.BookmarkFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BookmarkTest {

    @Test
    @DisplayName("정상적으로 북마크를 생성할 수 있다.")
    void createBookmark_Success() {
        //given
        Bookmark bookmark = BookmarkFixture.bookmarkBuilder();

        //when & then
        assertNotNull(bookmark.getUser());
        assertNotNull(bookmark.getPlace());
        assertEquals(BookmarkStatus.TRUE, bookmark.getStatus());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("정상적으로, 여러개의 북마크를 생성할 수 있다.")
    void createBookmarks_Success(int count) {
        //given
        List<Bookmark> bookmarks = BookmarkFixture.bookmarksBuilder(count);

        //when & then
        bookmarks.forEach(bookmark -> {
            assertNotNull(bookmark.getUser());
            assertNotNull(bookmark.getPlace());
            assertEquals(BookmarkStatus.TRUE, bookmark.getStatus());
        });
    }
}
