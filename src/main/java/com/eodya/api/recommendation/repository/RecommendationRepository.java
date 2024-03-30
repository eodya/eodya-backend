package com.eodya.api.recommendation.repository;

import com.eodya.api.place.domain.Place;
import com.eodya.api.recommendation.domain.Recommendation;
import com.eodya.api.recommendation.exception.RecommendationException;
import com.eodya.api.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

import static com.eodya.api.recommendation.exception.RecommendationExceptionCode.*;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    Optional<Recommendation> findByPlaceId(Long reviewId);
}
