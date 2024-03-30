package com.eodya.api.recommendation.dto.request;

import com.eodya.api.recommendation.domain.RecommendationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@Schema(description = "추천 생성/취소 요청")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendationChangeStatusRequest {

    @Schema(description = "생성(true) 또는 취소(false)")
    private boolean currentStatus;
}
