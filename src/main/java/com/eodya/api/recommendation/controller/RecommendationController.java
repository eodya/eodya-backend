package com.eodya.api.recommendation.controller;

import com.eodya.api.recommendation.dto.request.RecommendationChangeStatusRequest;
import com.eodya.api.recommendation.service.RecommendationService;
import com.eodya.api.users.config.Login;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recommendation")
public class RecommendationController {

    private final RecommendationService recommendationService;

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
