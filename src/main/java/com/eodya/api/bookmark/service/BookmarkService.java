package com.eodya.api.bookmark.service;

import com.eodya.api.bookmark.domain.Bookmark;
import com.eodya.api.bookmark.domain.BookmarkStatus;
import com.eodya.api.bookmark.dto.request.BookmarkChangeStatusRequest;
import com.eodya.api.bookmark.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public void updateBookmarkStatus(
            Long placeId,
            BookmarkChangeStatusRequest changeStatusRequest
    ) {
        Bookmark bookMark = bookmarkRepository.getBookMarkById(placeId);
        BookmarkStatus bookmarkStatus = changeStatusRequest.isCurrentStatus() ? BookmarkStatus.TRUE : BookmarkStatus.FALSE;
        bookMark.updateStatus(bookmarkStatus);
        bookmarkRepository.save(bookMark);
    }
}
