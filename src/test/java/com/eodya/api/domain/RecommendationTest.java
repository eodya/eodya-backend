package com.eodya.api.domain;

import com.eodya.api.fixture.domain.RecommendationFixture;
import com.eodya.api.recommendation.domain.Recommendation;
import com.eodya.api.recommendation.domain.RecommendationStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RecommendationTest {

    @Test
    @DisplayName("정상적으로 추천을 생성 할 수 있다.")
    void createRecommendation_Success() {
        //given
        Recommendation recommendation = RecommendationFixture.recommendationBuilder();

        //when & then
        assertNotNull(recommendation);
        assertEquals(RecommendationStatus.TRUE, recommendation.getStatus());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("정상적으로 여러 개의 추천을 생성할 수 있다.")
    void createRecommendations_Success(int count) {
        //given
        List<Recommendation> recommendations = RecommendationFixture.recommendationsBuilder(count);

        //when & then
        recommendations.forEach(recommendation -> {
            assertEquals(RecommendationStatus.TRUE, recommendation.getStatus());
        });
    }
}
