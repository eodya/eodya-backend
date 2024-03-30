package com.eodya.api.review.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@Schema(description = "리뷰 조회 응답 - 리뷰 상세 정보")
public class ReviewDetail {

    @Schema(description = "유저 ID")
    private Long userId;
    @Schema(description = "유저 닉네임")
    private String nickName;
    @Schema(description = "리뷰 작성일")
    private LocalDate reviewDate;
    @Schema(description = "리뷰 이미지")
    private List<String> reviewImage;
    @Schema(description = "리뷰 내용")
    private String reviewContent;
}
