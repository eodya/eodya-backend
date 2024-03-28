package com.eodya.api.review.exception;

import com.eodya.api.common.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewExceptionCode implements ExceptionCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REV-001", "리뷰를 찾을 수 없음"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
