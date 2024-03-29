package com.eodya.api.recommendation.dto.request;

import com.eodya.api.recommendation.domain.RecommendationStatus;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RecommendationChangeStatusRequest {

    private boolean currentStatus;
}
