package com.eodya.api.domain;

import com.eodya.api.fixture.domain.UserFixture;
import com.eodya.api.place.domain.Place;
import com.eodya.api.fixture.domain.PlaceFixture;
import com.eodya.api.users.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlaceTest {

    @Test
    @DisplayName("정상적으로 장소를 생성할 수 있다.")
    void createPlace_Success() {
        // given
        User user = UserFixture.userBuild();
        Place place = PlaceFixture.placeBuild(user);

        // when & then
        assertNotNull(place.getUser());
        assertNotNull(place.getPoint());
        assertNotNull(place.getAddressDetail());
        assertEquals(place.getReviewCount(), 0);
        assertEquals(place.getBookmarkCount(), 0);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("여러 개의 장소를 성공적으로 생성할 수 있다.")
    void createPlaces_Success(int count) {
        // given
        List<Place> places = PlaceFixture.placesBuild(count);

        // when & then
        assertEquals(count, places.size());
        places.forEach(place -> {
            assertNotNull(place.getUser());
            assertNotNull(place.getPoint());
            assertNotNull(place.getAddressDetail());
            assertEquals(place.getReviewCount(), 0);
            assertEquals(place.getBookmarkCount(), 0);
        });
    }
}
