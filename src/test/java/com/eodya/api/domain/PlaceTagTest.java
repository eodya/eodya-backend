package com.eodya.api.domain;

import com.eodya.api.address.domain.AddressDepth1;
import com.eodya.api.address.domain.AddressDepth2;
import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceTag;
import com.eodya.api.users.domain.OAuthProvider;
import com.eodya.api.users.domain.User;
import com.eodya.api.fixture.domain.AddressDepthFixture;
import com.eodya.api.fixture.domain.PlaceFixture;
import com.eodya.api.fixture.domain.PlaceTagFixture;
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

class PlaceTagTest {

    @Test
    @DisplayName("정상적으로 장소 태그를 생성할 수 있다.")
    void createPlaceTag_Success() {
        // given
        PlaceTag placeTag = PlaceTagFixture.placeTagBuild();

        // when & then
        assertNotNull(placeTag.getTag());
        assertNotNull(placeTag.getPlace());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void createPlaceTags_Success(int count) {
        //given
        List<PlaceTag> placeTags = PlaceTagFixture.placeTagsBuild(count);

        //when& then
        placeTags.forEach(placeTag -> {
            assertNotNull(placeTag.getTag());
            assertNotNull(placeTag.getPlace());
        });
    }
}
