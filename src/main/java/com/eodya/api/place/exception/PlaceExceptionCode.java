package com.eodya.api.place.exception;

import com.eodya.api.common.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PlaceExceptionCode implements ExceptionCode {

    PLACE_NOT_FOUNT(HttpStatus.NOT_FOUND,"PLA-001", "장소를 찾을 수 없음"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
