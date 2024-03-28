package com.eodya.api.users.exception;

import com.eodya.api.common.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserExceptionCode implements ExceptionCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USE-001", "해당하는 유저를 찾을 수 없음"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}


