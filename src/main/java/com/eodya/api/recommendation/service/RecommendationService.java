package com.eodya.api.recommendation.service;

import com.eodya.api.place.domain.Place;
import com.eodya.api.place.repository.PlaceRepository;
import com.eodya.api.recommendation.domain.Recommendation;
import com.eodya.api.recommendation.domain.RecommendationStatus;
import com.eodya.api.recommendation.dto.request.RecommendationChangeStatusRequest;
import com.eodya.api.recommendation.repository.RecommendationRepository;
import com.eodya.api.review.domain.Review;
import com.eodya.api.review.repository.ReviewRepository;
import com.eodya.api.users.domain.User;
import com.eodya.api.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    @Transactional
    public void updateRecommendationStatus(
            Long userId,
            Long placeId,
            RecommendationChangeStatusRequest changeStatusRequest
    ) {
        User user = userRepository.getUserById(userId);
        Place place = placeRepository.getPlaceById(placeId);

        Recommendation recommendation = recommendationRepository.findByPlaceId(placeId)
                .orElseGet(() -> Recommendation.builder()
                        .place(place)
                        .user(user)
                        .build());

        RecommendationStatus recommendationStatus = changeStatusRequest.isCurrentStatus() ? RecommendationStatus.TRUE : RecommendationStatus.FALSE;
        recommendation.updateStatus(recommendationStatus);

        recommendationRepository.save(recommendation);
    }
}
