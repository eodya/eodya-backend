package com.eodya.api.fixture.domain;

import com.eodya.api.address.domain.AddressDepth1;
import com.eodya.api.address.domain.AddressDepth2;
import com.eodya.api.place.domain.Place;
import com.eodya.api.users.domain.User;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PlaceFixture {

    private static final GeometryFactory geometryFactory = new GeometryFactory();

    public static Place placeBuild() {
        Point point = pointBuild(37.5665, 126.9780);
        String name = "테스트 스팟 장소";
        String addressDetail = "서울특별시 강남구 논현동";
        User user = UserFixture.userBuild();
        AddressDepth1 addressDepth1 = AddressDepthFixture.addressDepth1Build();
        AddressDepth2 addressDepth2 = AddressDepthFixture.addressDepth2Build();

        return Place.builder()
                .point(point)
                .name(name)
                .addressDetail(addressDetail)
                .user(user)
                .addressDepth1(addressDepth1)
                .addressDepth2(addressDepth2)
                .build();
    }

    public static List<Place> placesBuild(int count) {
        List<Place> places = new ArrayList<>();

        IntStream.range(0, count).forEach(i -> {
            places.add(placeBuild());
        });

        return places;
    }

    public static Point pointBuild(double x, double y) {
        Coordinate coordinate = new Coordinate(x, y);
        return geometryFactory.createPoint(coordinate);
    }
}
