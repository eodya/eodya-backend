package com.eodya.api.fixture.dto;

import com.eodya.api.place.domain.PlaceStatus;
import com.eodya.api.review.dto.request.ReviewCreateRequest;
import com.eodya.api.review.dto.response.ReviewDetail;
import com.eodya.api.review.dto.response.ReviewIdResponse;
import com.eodya.api.review.dto.response.ReviewProfileResponse;

import java.util.List;

public class ReviewDtoFixture {

    public static ReviewCreateRequest reviewCreateRequest(Long placeId, String reviewDate, PlaceStatus placeStatus, String reviewContent) {
        return ReviewCreateRequest.builder()
                .placeId(placeId)
                .reviewDate(reviewDate)
                .placeStatus(placeStatus)
                .reviewContent(reviewContent)
                .build();
    }

    public static ReviewIdResponse reviewIdResponse(Long reviewId) {
        return ReviewIdResponse.from(reviewId);
    }

    public static ReviewProfileResponse reviewProfileResponse(Long reviewTotalCount, List<ReviewDetail> reviewDetailList, boolean hasNext) {
        return ReviewProfileResponse.from(reviewTotalCount, reviewDetailList, hasNext);
    }
}
