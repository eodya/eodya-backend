package com.eodya.api.bookmark.repository;

import com.eodya.api.bookmark.domain.Bookmark;
import com.eodya.api.bookmark.domain.BookmarkStatus;
import com.eodya.api.bookmark.exception.BookmarkException;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import static com.eodya.api.bookmark.exception.BookmarkExceptionCode.*;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findByPlaceId(Long placeId);

    Page<Bookmark> findByUserIdAndStatus(Long userId, BookmarkStatus status, Pageable pageable);

    Optional<Bookmark> findByUserIdAndPlaceId(Long userId, Long placeId);
}
