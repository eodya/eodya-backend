package com.eodya.api.place.dto.response;

import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlaceDetail {

    Long placeId;
    String name;
    String addressDetail;
    String placeImage;
    boolean bookmarkStatus;
    PlaceStatus placeStatus;

    public static PlaceDetail from (Place place, boolean bookmarkStatus, PlaceStatus placeStatus) {
        return PlaceDetail.builder()
                .placeId(place.getId())
                .name(place.getName())
                .addressDetail(place.getAddressDetail())
                .placeImage(place.getImage())
                .bookmarkStatus(bookmarkStatus)
                .placeStatus(placeStatus)
                .build();
    }
}
