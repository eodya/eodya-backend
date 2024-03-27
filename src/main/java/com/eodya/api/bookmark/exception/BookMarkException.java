package com.eodya.api.bookmark.exception;

import com.eodya.api.common.exception.BusinessException;
import com.eodya.api.common.exception.ExceptionCode;

public class BookMarkException extends BusinessException {

    public BookMarkException(ExceptionCode exceptionCode, Object... rejectedValues) {
        super(exceptionCode, rejectedValues);
    }
}
