package com.eodya.api.place.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceAllByTagResponse {

    private Long placeId;
    private double x;
    private double y;
}
