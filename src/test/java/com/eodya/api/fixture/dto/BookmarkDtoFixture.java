package com.eodya.api.fixture.dto;

import com.eodya.api.bookmark.dto.request.BookmarkChangeStatusRequest;

public class BookmarkDtoFixture {

    public static BookmarkChangeStatusRequest bookmarkChangeStatusRequest(boolean currentStatus) {
        return BookmarkChangeStatusRequest.builder()
                .currentStatus(currentStatus)
                .build();
    }
}
