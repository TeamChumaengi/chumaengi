package com.Chumaengi.chumaengi.auth.exception;

import com.Chumaengi.chumaengi.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "AUTH_001", "이미 존재하는 이메일입니다."),
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT,"AUTH_002", "이미 존재하는 닉네임입니다."),
    WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, "AUTH_003", "비밀번호가 일치하지 않습니다."),
    AUTH_EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_004", "토큰의 유효기간이 만료되었습니다."),
    AUTH_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_005", "토큰이 유효하지 않습니다."),
    INVALID_PERMISSION(HttpStatus.FORBIDDEN, "AUTH_006", "권한이 없습니다. 로그인 먼저 해주세요.")
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
