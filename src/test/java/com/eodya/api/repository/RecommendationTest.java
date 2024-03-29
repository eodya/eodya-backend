package com.eodya.api.repository;

import com.eodya.api.fixture.domain.PlaceFixture;
import com.eodya.api.fixture.domain.RecommendationFixture;
import com.eodya.api.fixture.domain.UserFixture;
import com.eodya.api.place.domain.Place;
import com.eodya.api.place.repository.PlaceRepository;
import com.eodya.api.recommendation.domain.Recommendation;
import com.eodya.api.recommendation.domain.RecommendationStatus;
import com.eodya.api.recommendation.repository.RecommendationRepository;
import com.eodya.api.users.domain.User;
import com.eodya.api.users.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class RecommendationTest {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Test
    @DisplayName("정상적으로 생성된 Recommendation의 id를 가져올 수 있다.")
    void getRecommendation_ById_Success() {
        //given
        User testUser = UserFixture.userBuild();
        Place testPlace = PlaceFixture.placeBuild(testUser);
        userRepository.save(testUser);
        placeRepository.save(testPlace);

        Recommendation recommendation = RecommendationFixture.recommendationBuilder();
        recommendationRepository.save(recommendation);

        //when
        Recommendation findRecommendation = recommendationRepository.findById(recommendation.getId()).orElse(null);

        //then
        assertNotNull(findRecommendation);
        assertEquals(recommendation.getId(), findRecommendation.getId());
        assertEquals(RecommendationStatus.TRUE, findRecommendation.getStatus());
    }
}
