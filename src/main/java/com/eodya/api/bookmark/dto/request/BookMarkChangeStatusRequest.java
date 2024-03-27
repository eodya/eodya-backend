package com.eodya.api.bookmark.dto.request;

import com.eodya.api.bookmark.domain.BookMarkStatus;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookMarkChangeStatusRequest {

    private BookMarkStatus status;
}
