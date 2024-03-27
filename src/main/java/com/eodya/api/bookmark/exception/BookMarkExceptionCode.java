package com.eodya.api.bookmark.exception;

import com.eodya.api.common.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BookMarkExceptionCode implements ExceptionCode {

    BOOKMARK_STATUS_NOT_FOUND(HttpStatus.NOT_FOUND, "BOO-001", "북마크 상태를 찾을 수 없음"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
