package com.eodya.api.fixture.dto;

import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceStatus;
import com.eodya.api.place.dto.response.PlaceDetail;
import com.eodya.api.place.dto.response.PlaceRakingResponse;

public class PlaceDtoFixture {

    public static PlaceDetail placeDetail(Place place, boolean bookmarkStatus, PlaceStatus placeStatus) {
        return PlaceDetail.from(place, bookmarkStatus, placeStatus);
    }

    public static PlaceRakingResponse placeRakingResponse(Long id, String name, String placeImage, Long bookmarkCount, String addressDetail, Long rank) {
        return PlaceRakingResponse.builder()
                .id(id)
                .name(name)
                .placeImage(placeImage)
                .bookmarkCount(bookmarkCount)
                .addressDetail(addressDetail)
                .rank(rank)
                .build();
    }
}
