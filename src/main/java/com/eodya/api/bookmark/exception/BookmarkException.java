package com.eodya.api.bookmark.exception;

import com.eodya.api.common.exception.BusinessException;
import com.eodya.api.common.exception.ExceptionCode;

public class BookmarkException extends BusinessException {

    public BookmarkException(ExceptionCode exceptionCode, Object... rejectedValues) {
        super(exceptionCode, rejectedValues);
    }
}
