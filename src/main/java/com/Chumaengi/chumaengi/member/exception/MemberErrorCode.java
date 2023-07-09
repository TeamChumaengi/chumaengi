package com.Chumaengi.chumaengi.member.exception;

import com.Chumaengi.chumaengi.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_001", "회원 정보를 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
