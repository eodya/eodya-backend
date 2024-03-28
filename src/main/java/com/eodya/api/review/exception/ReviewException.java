package com.eodya.api.review.exception;

import com.eodya.api.common.exception.BusinessException;
import com.eodya.api.common.exception.ExceptionCode;

public class ReviewException extends BusinessException {

    public ReviewException(ExceptionCode exceptionCode, Object... rejectedValues) {
        super(exceptionCode, rejectedValues);
    }
}
