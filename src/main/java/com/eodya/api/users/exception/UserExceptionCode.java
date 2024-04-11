package com.eodya.api.users.exception;

import com.eodya.api.common.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserExceptionCode implements ExceptionCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USR-001", "사용자를 찾을 수 없음"),
    ALREADY_EXIST_NICKNAME(HttpStatus.BAD_REQUEST, "USR-002", "해당 닉네임으로 등록된 사용자가 있음."),
    USER_SIGNUP_OAUTH_SUBJECT_INVALID(HttpStatus.BAD_REQUEST, "USR-003",
            "회원 가입 시, 사용자의 OAuth ID와 Provider의 정보가 유효하지 않음"),
    USER_IS_EXISTED(HttpStatus.BAD_REQUEST, "USR-004", "이미 존재하는 사용자 정보"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

