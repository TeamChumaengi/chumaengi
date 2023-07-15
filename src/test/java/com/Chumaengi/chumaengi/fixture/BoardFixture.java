package com.Chumaengi.chumaengi.fixture;

import com.Chumaengi.chumaengi.board.domain.Board;
import com.Chumaengi.chumaengi.member.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoardFixture {
    BOARD_0("제목0", "내용0", "질문"),
    BOARD_1("제목1", "내용1", "공지사항"),
    BOARD_2("제목2", "내용2", "정보공유"),
    ;

    private final String title;
    private final String content;
    private final String category;

    public Board toBoard(Member writer) { return Board.createBoard(writer, title, content, category); }

}
