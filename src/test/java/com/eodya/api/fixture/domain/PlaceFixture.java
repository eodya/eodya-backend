package com.eodya.api.fixture.domain;

import com.eodya.api.entity.AddressDepth1;
import com.eodya.api.entity.AddressDepth2;
import com.eodya.api.entity.Place;
import com.eodya.api.entity.User;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

public class PlaceFixture {

    private static GeometryFactory geometryFactory = new GeometryFactory();
    public static Place placeBuild(
            Point point,
            String name,
            String image,
            String addressDetail,
            User user,
            AddressDepth1 addressDepth1,
            AddressDepth2 addressDepth2
    ) {
        return Place.builder()
                .point(point)
                .name(name)
                .image(image)
                .addressDetail(addressDetail)
                .user(user)
                .addressDepth1(addressDepth1)
                .addressDepth2(addressDepth2)
                .build();
    }

    public static Point pointBuild(double x, double y) {
        Coordinate coord = new Coordinate(x, y);
        return geometryFactory.createPoint(coord);

    }
}
