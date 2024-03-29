package com.eodya.api.fixture.domain;

import com.eodya.api.address.domain.AddressDepth1;
import com.eodya.api.address.domain.AddressDepth2;
import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceTag;
import com.eodya.api.place.domain.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PlaceTagFixture {

    public static PlaceTag placeTagBuild() {
        Place testPlace = PlaceFixture.placeBuild();

        Tag testTag = Tag.builder()
                .name("테스트 태그")
                .build();

        return PlaceTag.builder()
                .tag(testTag)
                .place(testPlace)
                .build();
    }

    public static List<PlaceTag> placeTagsBuild(int count) {
        List<PlaceTag> placeTags = new ArrayList<>();

        IntStream.range(0, count).forEach(i -> {
            placeTags.add(placeTagBuild());
        });

        return placeTags;
    }
}
