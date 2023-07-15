package com.Chumaengi.chumaengi.board.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    Question("질문"),
    BoardNotice("공지사항"),
    Information("정보공유")
    ;

    private final String value;
}
