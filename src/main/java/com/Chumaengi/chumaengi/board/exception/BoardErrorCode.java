package com.Chumaengi.chumaengi.board.exception;

import com.Chumaengi.chumaengi.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BoardErrorCode implements ErrorCode {
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "BOARD_001", "회원 정보를 찾을 수 없습니다."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "BOARD_002", "카테고리 정보를 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
