package com.eodya.api.bookmark.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@Schema(description = "북마크 생성/취소 요청")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookmarkChangeStatusRequest {

    @Schema(description = "생성(true) 또는 취소(false)")
    private boolean currentStatus;
}
