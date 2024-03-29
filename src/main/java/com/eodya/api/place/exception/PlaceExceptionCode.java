package com.eodya.api.place.exception;

import com.eodya.api.common.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PlaceExceptionCode implements ExceptionCode {

    PLACE_NOT_FOUNT(HttpStatus.NOT_FOUND,"PLA-001", "장소를 찾을 수 없음"),
    ALREADY_EXIST_PLACE(HttpStatus.BAD_REQUEST,"PLA-002", "이미 등록된 장소"),
    INVALID_PLACE_IMAGE_COUNT(HttpStatus.BAD_REQUEST, "PLA-003", "장소 등록 사진 개수 오류, 최소1장 최대2장"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
