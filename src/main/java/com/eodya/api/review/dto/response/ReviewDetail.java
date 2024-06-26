package com.eodya.api.review.dto.response;

import com.eodya.api.place.domain.PlaceStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class ReviewDetail {

    private Long userId;
    private String nickName;
    private LocalDate reviewDate;
    private List<String> reviewImage;
    private PlaceStatus placeStatus;
    private String reviewContent;
}
