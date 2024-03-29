package com.eodya.api.service;

import com.eodya.api.fixture.domain.RecommendationFixture;
import com.eodya.api.fixture.domain.ReviewFixture;
import com.eodya.api.fixture.dto.RecommendationDtoFixture;
import com.eodya.api.recommendation.domain.Recommendation;
import com.eodya.api.recommendation.dto.request.RecommendationChangeStatusRequest;
import com.eodya.api.recommendation.exception.RecommendationException;
import com.eodya.api.recommendation.exception.RecommendationExceptionCode;
import com.eodya.api.recommendation.repository.RecommendationRepository;
import com.eodya.api.recommendation.service.RecommendationService;
import com.eodya.api.review.domain.Review;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecommendationServiceTest {

    @Mock
    private RecommendationRepository recommendationRepository;

    @InjectMocks
    private RecommendationService recommendationService;

    @Test
    @DisplayName("해당 후기에 대해서 추천을 정상적으로 생성할 수 있다.")
    void createRecommendation_Success() {
        // given
        Review review = ReviewFixture.reviewBuild();
        Recommendation recommendation = RecommendationFixture.recommendationBuilder();

        when(recommendationRepository.getRecommendationById(review.getId())).thenReturn(recommendation);

        // when
        RecommendationChangeStatusRequest changeStatusRequest = RecommendationDtoFixture.recommendationChangeStatusRequest(true);
        recommendationService.updateRecommendationStatus(review.getId(), changeStatusRequest);

        // then
        assertTrue(changeStatusRequest.isCurrentStatus());
        verify(recommendationRepository).save(any(Recommendation.class));
    }

    @Test
    @DisplayName("해당 후기에 대해서 추천을 정상적으로 삭제할 수 있다.")
    void deleteRecommendation_Success() {
        // given
        Review review = ReviewFixture.reviewBuild();
        Recommendation recommendation = RecommendationFixture.recommendationBuilder();

        when(recommendationRepository.getRecommendationById(review.getId())).thenReturn(recommendation);

        // when
        RecommendationChangeStatusRequest changeStatusRequest = RecommendationDtoFixture.recommendationChangeStatusRequest(false);
        recommendationService.updateRecommendationStatus(review.getId(), changeStatusRequest);

        // then
        assertFalse(changeStatusRequest.isCurrentStatus());
        verify(recommendationRepository).save(any(Recommendation.class));
    }

    @Test
    @DisplayName("해당 후기에 추천할 수 없을 경우, 예외를 발생시킨다.")
    void createRecommendation_Fail_RecommendationIsNotCreate() {
        // given
        Long nonExistingReviewId = 999L;
        RecommendationChangeStatusRequest changeStatusRequest = RecommendationDtoFixture.recommendationChangeStatusRequest(true);

        when(recommendationRepository.getRecommendationById(nonExistingReviewId))
                .thenThrow(new RecommendationException(RecommendationExceptionCode.RECOMMENDATION_STATUS_NOT_FOUND, nonExistingReviewId));

        // when & then
        assertThatThrownBy(() -> recommendationService.updateRecommendationStatus(nonExistingReviewId, changeStatusRequest))
                .isInstanceOf(RecommendationException.class)
                .hasMessage(RecommendationExceptionCode.RECOMMENDATION_STATUS_NOT_FOUND.getMessage())
                .extracting(ex -> ((RecommendationException) ex).getExceptionCode().getCode())
                .isEqualTo(RecommendationExceptionCode.RECOMMENDATION_STATUS_NOT_FOUND.getCode());
    }
}
