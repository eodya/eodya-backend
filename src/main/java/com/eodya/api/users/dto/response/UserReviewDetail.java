package com.eodya.api.users.dto.response;

import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceStatus;
import com.eodya.api.review.domain.Review;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserReviewDetail {

    private Long placeId;
    private String reviewDate;
    private String image;
    private String name;
    private String reviewContent;
    private PlaceStatus placeStatus;

    public static UserReviewDetail from(
            Place place,
            Review review,
            PlaceStatus placeStatus
    ) {
        return UserReviewDetail.builder()
                .placeId(place.getId())
                .reviewDate(String.valueOf(review.getReviewDate()))
                .image(place.getImage())
                .name(place.getName())
                .reviewContent(review.getReviewContent())
                .placeStatus(placeStatus)
                .build();
    }
}
