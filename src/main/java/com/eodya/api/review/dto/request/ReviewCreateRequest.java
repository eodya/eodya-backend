package com.eodya.api.review.dto.request;

import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceStatus;
import com.eodya.api.review.domain.Review;
import com.eodya.api.users.domain.User;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewCreateRequest {

    private Long placeId;

    private String reviewDate;

    private PlaceStatus placeStatus;

    private String reviewContent;

    public Review toEntity(User user, Place place) {

        return Review.builder()
                .reviewDate(LocalDate.parse(reviewDate))
                .placeStatus(placeStatus)
                .reviewContent(reviewContent)
                .user(user)
                .place(place)
                .build();
    }
}
