package com.eodya.api.domain;

import com.eodya.api.address.domain.AddressDepth1;
import com.eodya.api.address.domain.AddressDepth2;
import com.eodya.api.place.domain.Place;
import com.eodya.api.users.domain.OAuthProvider;
import com.eodya.api.users.domain.User;
import com.eodya.api.fixture.domain.AddressDepthFixture;
import com.eodya.api.fixture.domain.PlaceFixture;
import com.eodya.api.fixture.domain.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.locationtech.jts.geom.Point;

import java.util.List;

import static com.eodya.api.users.domain.OAuthProvider.KAKAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlaceTest {

    @Test
    @DisplayName("정상적으로 장소를 생성할 수 있다.")
    void createPlace_Success() {
        // given
        Place testPlace = PlaceFixture.placeBuild();


        // when & then
        assertNotNull(testPlace.getUser());
        assertNotNull(testPlace.getPoint());
        assertNotNull(testPlace.getPoint());
        assertNotNull(testPlace.getAddressDetail());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("여러 개의 장소를 성공적으로 생성할 수 있다.")
    void createPlaces_Success(int count) {
        // given
        List<Place> testPlaces = PlaceFixture.placesBuild(count);

        // when & then
        assertEquals(count, testPlaces.size());
    }
}
