package com.eodya.api.users.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMyReviewsResponse {

    Long reviewTotalCount;
    List<UserReviewDetail> reviews;
    boolean hasNext;

    public static UserMyReviewsResponse from(
            Long reviewTotalCount,
            List<UserReviewDetail> reviews,
            boolean hasNext
    ) {
        return UserMyReviewsResponse.builder()
                .reviewTotalCount(reviewTotalCount)
                .reviews(reviews)
                .hasNext(hasNext)
                .build();
    }
}