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
import org.locationtech.jts.geom.Point;

import static com.eodya.api.users.domain.OAuthProvider.KAKAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class PlaceTagTest {

    @Test
    @DisplayName("정상적으로 장소 태그를 생성할 수 있다.")
    void createPlaceTag_Success() {
        //given
        String tagName = "테스트";
        Point point = PlaceFixture.pointBuild(10.0, 11.0);
        String placeName = "테스트 스팟 생성";
        String image = "이미지";
        String addressDetail = "서울시 동작구 머머머";
        OAuthProvider OAuthProvider = KAKAO;
        User user = UserFixture.userBuild("가희", "1234", KAKAO);
        AddressDepth1 addressDepth1 = AddressDepthFixture.addressDepth1Build("서울시");
        AddressDepth2 addressDepth2 = AddressDepthFixture.addressDepth2Build("동작구", addressDepth1);
        Place place = PlaceFixture.placeBuild(point, placeName, image, addressDetail, user, addressDepth1, addressDepth2);

        //when
        PlaceTag placeTag = PlaceTagFixture.placeTagBuild(tagName, place);

        //then
        assertNotNull(placeTag);
        assertEquals(placeTag.getName(), tagName);
        assertEquals(placeTag.getPlace(), place);

        //연관관계 확인
        assertEquals(place.getPlaceTags().size(), 1);
        assertEquals(place.getPlaceTags().get(0).getName(), tagName);
    }
}
