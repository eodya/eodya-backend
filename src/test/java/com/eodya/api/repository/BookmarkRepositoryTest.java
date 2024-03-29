package com.eodya.api.repository;

import com.eodya.api.bookmark.domain.Bookmark;
import com.eodya.api.bookmark.domain.BookmarkStatus;
import com.eodya.api.bookmark.repository.BookmarkRepository;
import com.eodya.api.fixture.domain.BookmarkFixture;
import com.eodya.api.fixture.domain.PlaceFixture;
import com.eodya.api.fixture.domain.UserFixture;
import com.eodya.api.place.domain.Place;
import com.eodya.api.place.repository.PlaceRepository;
import com.eodya.api.users.domain.User;
import com.eodya.api.users.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class BookmarkRepositoryTest {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Test
    @DisplayName("정상적으로 생성된 Bookmark의 id를 가져올 수 있다.")
    void getBookmark_ById_Success() {
        //given
        User testUser = UserFixture.userBuild();
        Place testPlace = PlaceFixture.placeBuild();
        userRepository.save(testUser);
        placeRepository.save(testPlace);

        Bookmark bookmark = BookmarkFixture.bookmarkBuilder();
        bookmarkRepository.save(bookmark);

        //when
        Bookmark findBookmark = bookmarkRepository.findById(bookmark.getId()).orElse(null);

        //then
        assertNotNull(findBookmark);
        assertEquals(bookmark.getId(), findBookmark.getId());
        assertEquals(BookmarkStatus.TRUE, findBookmark.getStatus());
    }
}
