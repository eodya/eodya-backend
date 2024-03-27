package com.eodya.api.bookmark.dto.request;

import com.eodya.api.bookmark.domain.BookmarkStatus;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookMarkChangeStatusRequest {

    private BookmarkStatus status;
}
