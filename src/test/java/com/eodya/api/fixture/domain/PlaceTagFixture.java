package com.eodya.api.fixture.domain;

import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceTag;

public class PlaceTagFixture {

    public static PlaceTag placeTagBuild(String name, Place place) {
        return PlaceTag.builder()
                .name(name)
                .place(place)
                .build();
    }
}
