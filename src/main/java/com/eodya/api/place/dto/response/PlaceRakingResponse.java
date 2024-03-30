package com.eodya.api.place.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "장소 랭킹 응답 - 북마크 개수 기준 랭킹 정보")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceRakingResponse {

    @Schema(description = "장소 ID")
    private Long id;
    @Schema(description = "장소 이름")
    private String name;
    @Schema(description = "장소 이미지")
    private String placeImage;
    @Schema(description = "북마크 개수")
    private Long bookmarkCount;
    @Schema(description = "장소 주소")
    private String addressDetail;
    @Schema(description = "장소 랭킹")
    private Long rank;
}
