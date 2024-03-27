package com.eodya.api.recommendation.exception;

import com.eodya.api.common.exception.BusinessException;
import com.eodya.api.common.exception.ExceptionCode;

public class RecommendationException extends BusinessException {

    public RecommendationException(ExceptionCode exceptionCode, Object... rejectedValues) {
        super(exceptionCode, rejectedValues);
    }
}
