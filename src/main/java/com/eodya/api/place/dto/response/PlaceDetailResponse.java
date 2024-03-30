package com.eodya.api.place.dto.response;

import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceDetailResponse {

    private String name;
    private String addressDetail;
    private String image;
    private PlaceStatus placeStatus;
    private long bookmarkCount;
    private boolean bookmarkStatus;

    public static PlaceDetailResponse from(
            Place place,
            PlaceStatus placeStatus,
            boolean bookmarkStatus
    ) {
        return PlaceDetailResponse.builder()
                .name(place.getName())
                .addressDetail(place.getAddressDetail())
                .image(place.getImage())
                .placeStatus(placeStatus)
                .bookmarkCount(place.getBookmarkCount())
                .bookmarkStatus(bookmarkStatus)
                .build();
    }
}