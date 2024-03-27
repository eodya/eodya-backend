package com.eodya.api.bookmark.repository;

import com.eodya.api.bookmark.domain.Bookmark;
import com.eodya.api.bookmark.exception.BookmarkException;
import org.springframework.data.jpa.repository.JpaRepository;

import static com.eodya.api.bookmark.exception.BookmarkExceptionCode.*;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    default Bookmark getBookMarkById(Long bookMarkId) {
        return findById(bookMarkId).orElseThrow(() -> new BookmarkException(BOOKMARK_STATUS_NOT_FOUND, bookMarkId));
    }
}
