package com.eodya.api.review.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewProfileResponse {

    private Long reviewTotalCount;
    private List<ReviewDetail> reviewDetailList;
    private boolean hasNext;

    public static ReviewProfileResponse from(final Long reviewTotalCount, final List<ReviewDetail> reviewDetailList, final boolean hasNext) {
        return ReviewProfileResponse.builder()
                .reviewTotalCount(reviewTotalCount)
                .reviewDetailList(reviewDetailList)
                .hasNext(hasNext)
                .build();
    }
}
