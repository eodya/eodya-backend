package com.eodya.api.repository;

import com.eodya.api.fixture.domain.PlaceFixture;
import com.eodya.api.fixture.domain.ReviewFixture;
import com.eodya.api.fixture.domain.UserFixture;
import com.eodya.api.place.domain.Place;
import com.eodya.api.place.repository.PlaceRepository;
import com.eodya.api.review.domain.Review;
import com.eodya.api.review.repository.ReviewRepository;
import com.eodya.api.users.domain.User;
import com.eodya.api.users.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    void setUp() {
        User testUser = UserFixture.userBuild();
        userRepository.save(testUser);

        Place testPlace = PlaceFixture.placeBuild(testUser);
        placeRepository.save(testPlace);

        em.persist(testUser);
        em.persist(testPlace);

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("정상적으로 장소의 생성된 리뷰를 가져올 수 있다.")
    void getReview_ByPlaceId_Success() {
        //given
        User testUser = UserFixture.userBuild();
        Place testPlace = PlaceFixture.placeBuild(testUser);

        Review testReview = ReviewFixture.reviewBuild();
        testReview.setPlace(testPlace);

        System.out.println("testReview.getPlace() = " + testReview.getPlace());

        reviewRepository.save(testReview);

        Pageable pageable = PageRequest.of(0, 10);

        //when
        Page<Review> reviewPage = reviewRepository.findReviewsByPlace(testPlace, pageable);

        //then
        assertNotNull(reviewPage);
        assertFalse(reviewPage.isEmpty());
        assertEquals(testReview.getId(), reviewPage.getContent().get(0).getId());
    }
}
