package com.Chumaengi.chumaengi.auth.exception;

import com.Chumaengi.chumaengi.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "AUTH_001", "이미 존재하는 이메일입니다."),
    WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, "AUTH_002", "비밀번호가 일치하지 않습니다.")
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
