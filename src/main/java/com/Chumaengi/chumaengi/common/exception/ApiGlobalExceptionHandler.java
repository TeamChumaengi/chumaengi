package com.Chumaengi.chumaengi.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiGlobalExceptionHandler {
    @ExceptionHandler(ChumaengiException.class)
    public ResponseEntity<ErrorResponse> studyWithMeException(ChumaengiException exception) {
        ErrorCode code = exception.getCode();
        return ResponseEntity
                .status(code.getStatus())
                .body(ErrorResponse.from(code));
    }
}
