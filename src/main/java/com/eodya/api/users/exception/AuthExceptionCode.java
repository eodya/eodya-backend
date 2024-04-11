package com.eodya.api.users.exception;

import com.eodya.api.common.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthExceptionCode implements ExceptionCode {

    INVALID_KAKAO_TOKEN(HttpStatus.BAD_REQUEST, "AUT-001", "유효한 카카오 토큰이 아님"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUT-002", "유효한 토큰이 아님"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "AUT-003", "만료된 토큰"),
    JWT_TOKEN_ERROR(HttpStatus.BAD_REQUEST, "AUT-004", "Jwt token error"),
    OAUTH_PROVIDER_NOT_FOUND(HttpStatus.NOT_FOUND,"AUT-005", "OAuthProvider를 찾을 수 없음"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
