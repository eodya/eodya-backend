package com.eodya.api.bookmark.service;

import com.eodya.api.bookmark.domain.BookMark;
import com.eodya.api.bookmark.dto.request.BookMarkChangeStatusRequest;
import com.eodya.api.bookmark.repository.BookMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookMarkRepository bookmarkRepository;

    public void updateBookmarkStatus(
            Long placeId,
            BookMarkChangeStatusRequest changeStatusRequest
    ) {
        BookMark bookMark = bookmarkRepository.getBookMarkById(placeId);
    }
}
