package com.eodya.api.recommendation.controller;

import com.eodya.api.recommendation.dto.request.RecommendationChangeStatusRequest;
import com.eodya.api.recommendation.service.RecommendationService;
import com.eodya.api.users.config.Login;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recommendation")
@Tag(name = "Recommendation", description = "추천 API")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Operation(
            summary = "추천 API",
            description = "해당 후기에 대해서 추천을 생성하거나 삭제합니다."
    )

    @PatchMapping("/{reviewId}")
    public ResponseEntity<Void> updateRecommendationStatus(
            @Login Long loggedInMemberId,
            @PathVariable Long reviewId,
            @Valid @RequestBody RecommendationChangeStatusRequest changeStatusRequest
    ) {
        recommendationService.updateRecommendationStatus(
                reviewId,
                changeStatusRequest
        );

        return ResponseEntity.status(OK)
                .build();
    }
}
