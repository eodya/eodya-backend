package com.eodya.api.bookmark.repository;

import com.eodya.api.bookmark.domain.BookMark;
import com.eodya.api.bookmark.exception.BookMarkException;
import org.springframework.data.jpa.repository.JpaRepository;

import static com.eodya.api.bookmark.exception.BookMarkExceptionCode.*;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {

    default BookMark getBookMarkById(Long bookMarkId) {
        return findById(bookMarkId).orElseThrow(() -> new BookMarkException(BOOKMARK_STATUS_NOT_FOUND, bookMarkId));
    }
}
