package com.eodya.api.users.dto.response;

import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserBookmarkDetail {

    private Long placeId;
    private String image;
    private String name;
    private String addressDetail;
    private int bookmarkCount;
    private boolean bookmarkStatus;
    private PlaceStatus placeStatus;

    public static UserBookmarkDetail from(Place place, PlaceStatus placeStatus) {
        return UserBookmarkDetail.builder()
                .placeId(place.getId())
                .image(place.getImage())
                .name(place.getName())
                .addressDetail(place.getAddressDetail())
                .bookmarkCount(place.getBookmarkCount())
                .bookmarkStatus(true)
                .placeStatus(placeStatus)
                .build();
    }
}
