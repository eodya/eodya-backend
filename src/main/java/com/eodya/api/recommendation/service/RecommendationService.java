package com.eodya.api.recommendation.service;

import com.eodya.api.recommendation.domain.Recommendation;
import com.eodya.api.recommendation.dto.request.RecommendationChangeStatusRequest;
import com.eodya.api.recommendation.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;

    public void updateRecommendationStatus(
            Long reviewId,
            RecommendationChangeStatusRequest changeStatusRequest
    ) {
        Recommendation recommendation = recommendationRepository.getRecommendationById(reviewId);
    }
}
