package com.eodya.api.bookmark.service;

import com.eodya.api.bookmark.domain.Bookmark;
import com.eodya.api.bookmark.dto.request.BookMarkChangeStatusRequest;
import com.eodya.api.bookmark.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public void updateBookmarkStatus(
            Long placeId,
            BookMarkChangeStatusRequest changeStatusRequest
    ) {
        Bookmark bookMark = bookmarkRepository.getBookMarkById(placeId);
    }
}
