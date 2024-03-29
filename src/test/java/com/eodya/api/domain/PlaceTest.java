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
import org.locationtech.jts.geom.Point;

import static com.eodya.api.users.domain.OAuthProvider.KAKAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlaceTest {

    String placeName = "테스트 스팟 생성";
    String image = "이미지";
    OAuthProvider OAuthProvider = KAKAO;
    User user = UserFixture.userBuild("가희", "1234", OAuthProvider);



}
