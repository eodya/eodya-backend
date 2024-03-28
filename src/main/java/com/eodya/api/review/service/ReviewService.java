package com.eodya.api.review.service;

import com.eodya.api.common.service.S3Service;
import com.eodya.api.place.domain.Place;
import com.eodya.api.place.exception.PlaceException;
import com.eodya.api.place.exception.PlaceExceptionCode;
import com.eodya.api.place.repository.PlaceRepository;
import com.eodya.api.review.domain.Review;
import com.eodya.api.review.domain.ReviewImage;
import com.eodya.api.review.dto.request.ReviewCreateRequest;
import com.eodya.api.review.dto.response.ReviewIdResponse;
import com.eodya.api.review.repository.ReviewImageRepository;
import com.eodya.api.review.repository.ReviewRepository;
import com.eodya.api.users.domain.User;
import com.eodya.api.users.exception.UserException;
import com.eodya.api.users.exception.UserExceptionCode;
import com.eodya.api.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewImageRepository reviewImageRepository;
    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;

    @Transactional
    public ReviewIdResponse createReview(ReviewCreateRequest reviewCreateRequest, Long loggedInMemberId, List<MultipartFile> images) {

        validateUserIsExist(loggedInMemberId);
        User user = userRepository.getUserById(loggedInMemberId);

        validatePlaceIsExist(reviewCreateRequest.getPlaceId());
        Place place = placeRepository.getPlaceById(reviewCreateRequest.getPlaceId());

        Review review = reviewRepository.save(reviewCreateRequest.toEntity(user, place));
        Review savedReview = reviewRepository.save(review);

        if (images != null && !images.isEmpty()) {
            for (MultipartFile imageFile : images) {
                String imageUrl = s3Service.uploadFile(imageFile);

                ReviewImage reviewImage = ReviewImage.builder()
                        .imageUrl(imageUrl)
                        .review(savedReview)
                        .build();

                reviewImageRepository.save(reviewImage);
            }
        }
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
