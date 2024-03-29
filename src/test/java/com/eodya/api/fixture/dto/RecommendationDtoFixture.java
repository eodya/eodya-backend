package com.eodya.api.fixture.dto;

import com.eodya.api.recommendation.dto.request.RecommendationChangeStatusRequest;

public class RecommendationDtoFixture {

    public static RecommendationChangeStatusRequest recommendationChangeStatusRequest(boolean currentStatus) {
        return RecommendationChangeStatusRequest.builder()
                .currentStatus(currentStatus)
                .build();
    }
}
