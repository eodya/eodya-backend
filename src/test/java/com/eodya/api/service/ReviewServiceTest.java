package com.eodya.api.service;

import com.eodya.api.common.service.S3Service;
import com.eodya.api.fixture.domain.PlaceFixture;
import com.eodya.api.fixture.domain.RecommendationFixture;
import com.eodya.api.fixture.domain.ReviewFixture;
import com.eodya.api.fixture.domain.UserFixture;
import com.eodya.api.fixture.dto.RecommendationDtoFixture;
import com.eodya.api.fixture.dto.ReviewDtoFixture;
import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceStatus;
import com.eodya.api.place.repository.PlaceRepository;
import com.eodya.api.recommendation.domain.Recommendation;
import com.eodya.api.recommendation.dto.request.RecommendationChangeStatusRequest;
import com.eodya.api.review.domain.Review;
import com.eodya.api.review.domain.ReviewImage;
import com.eodya.api.review.dto.request.ReviewCreateRequest;
import com.eodya.api.review.dto.response.ReviewIdResponse;
import com.eodya.api.review.repository.ReviewImageRepository;
import com.eodya.api.review.repository.ReviewRepository;
import com.eodya.api.review.service.ReviewService;
import com.eodya.api.users.domain.User;
import com.eodya.api.users.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewImageRepository reviewImageRepository;

    @Mock
    private PlaceRepository placeRepository;

    @Mock
    private S3Service s3Service;

    @Mock
    private ReviewService reviewService;

    @Test
    @DisplayName("해당 장소의 리뷰에 대해서 정상적으로 생성할 수 있다.")
    void createReview_Success() {
        // given
        User user = UserFixture.userBuild();
        userRepository.save(user);

        Place place = PlaceFixture.placeBuild(user);
        ReflectionTestUtils.setField(place, "id", 1L);
        placeRepository.save(place);

        // when
        ReviewCreateRequest reviewCreateRequest = ReviewDtoFixture.reviewCreateRequest(place.getId(), LocalDate.now().toString(), PlaceStatus.BLOOMING, "테스트 리뷰 내용");
        reviewService.createReview(reviewCreateRequest, user.getId(), null);

        // then
        assertNotNull(reviewCreateRequest);
        assertTrue(reviewCreateRequest.getPlaceId().equals(place.getId()));
    }

    @Test
    @DisplayName("해당 장소의 리뷰에 정상적으로 이미지를 추가할 수 있다.")
    void createReviewWithImage_Success() {
        // given
        User user = UserFixture.userBuild();
        userRepository.save(user);

        Place place = PlaceFixture.placeBuild(user);
        ReflectionTestUtils.setField(place, "id", 1L);
        placeRepository.save(place);


        Review review = ReviewFixture.reviewBuild();
        ReflectionTestUtils.setField(review, "id", 1L);
//        when(reviewRepository.save(review)).thenReturn(review);

        MultipartFile imageFile = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test image content".getBytes());
        List<MultipartFile> images = Arrays.asList(imageFile);

        String expectedImageUrl = "http://example.com/test.jpg";
//        when(s3Service.uploadFiles(eq(images))).thenReturn(List.of(expectedImageUrl));

        // when
        ReviewCreateRequest reviewCreateRequest = ReviewDtoFixture.reviewCreateRequest(place.getId(), LocalDate.now().toString(), PlaceStatus.BLOOMING, "테스트 리뷰 내용");
        ReviewIdResponse response = reviewService.createReview(reviewCreateRequest, user.getId(), images);

        //verify(s3Service).uploadFiles(eq(images));
    }


    @Test
    @DisplayName("해당 장소의 리뷰에 대해서 정상적으로 불러올 수 있다.")
    void getReviewByPlaceId_Success() {

    }

    @Test
    @DisplayName("해당 장소의 리뷰 업로드에 실패하면, 에러코드를 반환한다.")
    void createReviewWithImage_Fail() {

    }

    @Test
    @DisplayName("해당 장소의 리뷰에 대해서 찾을 수 없을 경우, 에러를 반환한다.")
    void getReviewByPlaceId_Fail() {

    }

    @Test
    @DisplayName("해당 장소의 리뷰 이미지를 찾을 수 없을 경우, 에러를 반환한다.")
    void getReviewImageByPlaceId_Fail() {
    }
}
