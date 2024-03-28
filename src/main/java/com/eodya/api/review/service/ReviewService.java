package com.eodya.api.review.service;

import com.eodya.api.place.domain.Place;
import com.eodya.api.place.exception.PlaceException;
import com.eodya.api.place.exception.PlaceExceptionCode;
import com.eodya.api.place.repository.PlaceRepository;
import com.eodya.api.review.domain.Review;
import com.eodya.api.review.dto.request.ReviewCreateRequest;
import com.eodya.api.review.dto.response.ReviewIdResponse;
import com.eodya.api.review.repository.ReviewRepository;
import com.eodya.api.users.domain.User;
import com.eodya.api.users.exception.UserException;
import com.eodya.api.users.exception.UserExceptionCode;
import com.eodya.api.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewIdResponse createReview(ReviewCreateRequest reviewCreateRequest, Long loggedInMemberId) {

        validateUserIsExist(loggedInMemberId);
        User user = userRepository.getUserById(loggedInMemberId);

        validatePlaceIsExist(reviewCreateRequest.getPlaceId());
        Place place = placeRepository.getPlaceById(reviewCreateRequest.getPlaceId());

        Review review = reviewRepository.save(reviewCreateRequest.toEntity(user, place));
        return ReviewIdResponse.from(review.getId());
    }

    private void validateUserIsExist(Long userId) {
        userRepository.findById(userId).orElseThrow(() ->
                new UserException(UserExceptionCode.USER_NOT_FOUND, userId)
        );
    }

    private void validatePlaceIsExist(Long placeId) {
        placeRepository.findById(placeId).orElseThrow(() ->
                new PlaceException(PlaceExceptionCode.PLACE_NOT_FOUNT, placeId)
        );
    }
}
