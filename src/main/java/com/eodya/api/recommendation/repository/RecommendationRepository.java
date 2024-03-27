package com.eodya.api.recommendation.repository;

import com.eodya.api.recommendation.domain.Recommendation;
import com.eodya.api.recommendation.exception.RecommendationException;
import org.springframework.data.jpa.repository.JpaRepository;


import static com.eodya.api.recommendation.exception.RecommendationExceptionCode.*;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    default Recommendation getRecommendationById(Long recommendationId) {
        return findById(recommendationId).orElseThrow(() -> new RecommendationException(RECOMMENDATION_STATUS_NOT_FOUND, recommendationId));
    }
}
