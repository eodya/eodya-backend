package com.eodya.api.review.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "리뷰 생성 응답 - 리뷰 ID")
@AllArgsConstructor(staticName = "from")
public class ReviewIdResponse {

    @Schema(description = "리뷰 ID")
    private Long reviewId;
}
