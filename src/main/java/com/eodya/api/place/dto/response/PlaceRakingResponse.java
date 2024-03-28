package com.eodya.api.place.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceRakingResponse {

    private Long id;
    private String name;
    private String placeImage;
    private Long bookmarkCount;
    private String addressDetail;
    private Long rank;
}
