package com.eodya.api.place.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceAllByAddressResponse {

    private List<PlaceDetail> placeDetails;
    private boolean hasNext;

    public static PlaceAllByAddressResponse from(List<PlaceDetail> placeDetails, boolean hasNext) {
        return PlaceAllByAddressResponse.builder()
                .placeDetails(placeDetails)
                .hasNext(hasNext)
                .build();
    }
}
