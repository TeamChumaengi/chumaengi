package com.Chumaengi.chumaengi.global.exception;

import lombok.Getter;

@Getter
public class ChumaengiException extends RuntimeException {
    private final ErrorCode code;

    public ChumaengiException(ErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public static ChumaengiException type(ErrorCode code) {
        return new ChumaengiException(code);
    }
}
