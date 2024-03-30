package com.eodya.api.review.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Schema(description = "리뷰 조회 응답 - 리뷰 목록 정보")
public class ReviewProfileResponse {

    @Schema(description = "리뷰 총 개수")
    private Long reviewTotalCount;
    @Schema(description = "리뷰 상세 정보")
    private List<ReviewDetail> reviewDetailList;
    @Schema(description = "다음 페이지 존재 여부")
    private boolean hasNext;

    public static ReviewProfileResponse from(final Long reviewTotalCount, final List<ReviewDetail> reviewDetailList, final boolean hasNext) {
        return ReviewProfileResponse.builder()
                .reviewTotalCount(reviewTotalCount)
                .reviewDetailList(reviewDetailList)
                .hasNext(hasNext)
                .build();
    }
}
