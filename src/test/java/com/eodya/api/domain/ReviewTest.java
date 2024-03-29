package com.eodya.api.domain;

import com.eodya.api.fixture.domain.ReviewFixture;
import com.eodya.api.review.domain.Review;
import com.eodya.api.review.domain.ReviewImage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {

    @Test
    @DisplayName("정상적으로 리뷰를 생성할 수 있다.")
    void createReview_Success() {
        //given
        Review review = ReviewFixture.reviewBuild();

        //when & then
        assertNotNull(review.getReviewDate());
        assertNotNull(review.getPlaceStatus());
        assertNotNull(review.getReviewContent());
        assertNotNull(review.getPlace());
        assertNotNull(review.getUser());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("여러 개의 리뷰를 성공적으로 생성할 수 있다.")
    void createReviews_Success(int count) {
        //given
        List<Review> reviews = ReviewFixture.reviewsBuild(count);

        //when & then
        assertNotNull(reviews);
        assertEquals(count, reviews.size());
        reviews.forEach(review -> {
            assertNotNull(review.getReviewDate());
            assertNotNull(review.getPlaceStatus());
            assertNotNull(review.getReviewContent());
            assertNotNull(review.getPlace());
            assertNotNull(review.getUser());
        });
    }

    @Test
    @DisplayName("정상적으로 리뷰에 이미지를 추가할 수 있다.")
    void addImageToReview_Success() {
        //given
        Review review = ReviewFixture.reviewBuild();
        ReviewImage reviewImage = ReviewFixture.reviewImageBuild(review);

        //when & then
        assertTrue(review.getImages().get(0).getImageUrl().contains("http://example.com/image.jpg"));
    }

    @Test
    @DisplayName("정상적으로 리뷰에 이미지를 여러 개 추가할 수 있다.")
    void addImagesToReview_Success() {
        //given
        Review review = ReviewFixture.reviewWithImagesBuild(3);

        //when&when
        assertTrue(review.getImages().get(0).getImageUrl().contains("http://example.com/image"));
        assertTrue(review.getImages().get(1).getImageUrl().contains("http://example.com/image"));
        assertTrue(review.getImages().get(2).getImageUrl().contains("http://example.com/image"));
    }
}
