package com.eodya.api.place.exception;

import com.eodya.api.common.exception.BusinessException;
import com.eodya.api.common.exception.ExceptionCode;

public class PlaceException extends BusinessException {

    public PlaceException(ExceptionCode exceptionCode, Object... rejectedValues) {
        super(exceptionCode, rejectedValues);
    }
}
