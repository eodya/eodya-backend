package com.eodya.api.service;

import com.eodya.api.bookmark.domain.Bookmark;
import com.eodya.api.bookmark.dto.request.BookmarkChangeStatusRequest;
import com.eodya.api.bookmark.exception.BookmarkException;
import com.eodya.api.bookmark.exception.BookmarkExceptionCode;
import com.eodya.api.bookmark.repository.BookmarkRepository;
import com.eodya.api.bookmark.service.BookmarkService;
import com.eodya.api.fixture.domain.BookmarkFixture;
import com.eodya.api.fixture.domain.PlaceFixture;
import com.eodya.api.fixture.domain.UserFixture;
import com.eodya.api.fixture.dto.BookmarkDtoFixture;
import com.eodya.api.place.domain.Place;
import com.eodya.api.users.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookmarkServiceTest {

    @Mock
    private BookmarkRepository bookmarkRepository;

    @InjectMocks
    private BookmarkService bookmarkService;

    @Test
    @DisplayName("해당 장소가 북마크 되어있지 않은 경우, 북마크를 생성할 수 있다.")
    void createBookmark_Success() {
        // given
        User user = UserFixture.userBuild();
        Place place = PlaceFixture.placeBuild(user);
        Bookmark bookmark = BookmarkFixture.bookmarkBuilder();

        when(bookmarkRepository.getBookMarkById(place.getId())).thenReturn(bookmark);

        //when
        BookmarkChangeStatusRequest changeStatusRequest = BookmarkDtoFixture.bookmarkChangeStatusRequest(true);
        bookmarkService.updateBookmarkStatus(place.getId(), changeStatusRequest);

        // then
        assertTrue(changeStatusRequest.isCurrentStatus());
        verify(bookmarkRepository).save(any(Bookmark.class));
    }

    @Test
    @DisplayName("해당 장소가 이미 북마크 되어있는 경우, 북마크를 삭제할 수 있다.")
    void deleteBookmark_Success() {
        // given
        User user = UserFixture.userBuild();
        Place place = PlaceFixture.placeBuild(user);
        Bookmark bookmark = BookmarkFixture.bookmarkBuilder();

        when(bookmarkRepository.getBookMarkById(place.getId())).thenReturn(bookmark);

        //when
        BookmarkChangeStatusRequest changeStatusRequest = BookmarkDtoFixture.bookmarkChangeStatusRequest(false);
        bookmarkService.updateBookmarkStatus(place.getId(), changeStatusRequest);

        // then
        assertFalse(changeStatusRequest.isCurrentStatus());
        verify(bookmarkRepository).save(any(Bookmark.class));
    }

    @Test
    @DisplayName("해당 장소에 북마크를 생성할 수 없을 경우, 에러 코드를 반환한다.")
    void createBookmark_Fail_BookmarkIsNotCreate() {
        // given
        Long nonExistingPlaceId = 999L;
        BookmarkChangeStatusRequest changeStatusRequest = BookmarkDtoFixture.bookmarkChangeStatusRequest(true);

        when(bookmarkRepository.getBookMarkById(nonExistingPlaceId))
                .thenThrow(new BookmarkException(BookmarkExceptionCode.BOOKMARK_STATUS_NOT_FOUND, nonExistingPlaceId));

        // when & then
        assertThatThrownBy(() -> bookmarkService.updateBookmarkStatus(nonExistingPlaceId, changeStatusRequest))
                .isInstanceOf(BookmarkException.class)
                .hasMessage(BookmarkExceptionCode.BOOKMARK_STATUS_NOT_FOUND.getMessage())
                .extracting(ex -> ((BookmarkException) ex).getExceptionCode().getCode())
                .isEqualTo(BookmarkExceptionCode.BOOKMARK_STATUS_NOT_FOUND.getCode());
    }
}
