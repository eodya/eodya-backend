package com.eodya.api.domain;

import com.eodya.api.entity.AddressDepth1;
import com.eodya.api.entity.AddressDepth2;
import com.eodya.api.entity.Place;
import com.eodya.api.entity.User;
import com.eodya.api.fixture.domain.AddressDepthFixture;
import com.eodya.api.fixture.domain.PlaceFixture;
import com.eodya.api.fixture.domain.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlaceTest {

    @Test
    @DisplayName("정상적으로 장소를 생성할 수 있다.")
    void createPlace_Success() {
        //given
        Point point = PlaceFixture.pointBuild(10.0, 11.0);
        String placeName = "테스트 스팟 생성";
        String image = "이미지";
        String addressDetail = "서울시 동작구 머머머";
        User user = UserFixture.userBuild("가희", "1234", "KAKAO");
        AddressDepth1 addressDepth1 = AddressDepthFixture.addressDepth1Build("서울시");
        AddressDepth2 addressDepth2 = AddressDepthFixture.addressDepth2Build("동작구", addressDepth1);

        //when
        Place place = PlaceFixture.placeBuild(point, placeName, image, addressDetail, user, addressDepth1, addressDepth2);

        //then
        assertNotNull(place);
        assertEquals(place.getName(), placeName);
        assertEquals(place.getRecommendCount(), 0);
        assertEquals(place.getBookmarkCount(), 0);
        assertEquals(place.getUser(), user);
        assertEquals(place.getDepth1(), addressDepth1);
        assertEquals(place.getDepth2(), addressDepth2);
    }

}
