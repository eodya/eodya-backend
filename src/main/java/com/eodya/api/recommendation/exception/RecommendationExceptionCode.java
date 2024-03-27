package com.eodya.api.recommendation.exception;

import com.eodya.api.common.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RecommendationExceptionCode implements ExceptionCode {

    RECOMMENDATION_STATUS_NOT_FOUND(HttpStatus.NOT_FOUND, "REC-001", "추천 상태를 찾을 수 없음"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
