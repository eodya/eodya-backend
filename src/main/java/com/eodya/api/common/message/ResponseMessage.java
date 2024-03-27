package com.eodya.api.common.message;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage {
    /** auth **/
    SUCCESS_SIGN_UP("회원 가입 성공"),
    SUCCESS_LOGIN("로그인 성공");

    /** user **/
    private final String message;
}


