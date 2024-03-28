package com.eodya.api.review.exception;

import com.eodya.api.common.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewExceptionCode implements ExceptionCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REV-001", "리뷰를 찾을 수 없음"),
    REVIEW_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "REV-002", "리뷰 이미지를 찾을 수 없음"),
    REVIEW_IMAGE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "REV-003", "리뷰 이미지 업로드에 실패함"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
