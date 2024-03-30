package com.eodya.api.review.dto.request;

import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceStatus;
import com.eodya.api.review.domain.Review;
import com.eodya.api.users.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@Schema(description = "리뷰 생성 요청")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewCreateRequest {

    @Schema(description = "장소 ID")
    private Long placeId;

    @Schema(description = "리뷰 작성일")
    private String reviewDate;

    @Schema(description = "장소 상태")
    private PlaceStatus placeStatus;

    @Schema(description = "리뷰 내용")
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
