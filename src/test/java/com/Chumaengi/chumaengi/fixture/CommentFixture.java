package com.Chumaengi.chumaengi.fixture;

import com.Chumaengi.chumaengi.board.domain.Board;
import com.Chumaengi.chumaengi.comment.domain.Comment;
import com.Chumaengi.chumaengi.member.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentFixture {
    COMMENT_0("댓글0", null),
    COMMENT_1("댓글1", null),
    COMMENT_2("댓글2", null),
    ;

    private final String content;
    private final Comment parent;

    public Comment toComment(Member writer, Board board) {
        return Comment.createComment(writer, board, parent, content);
    }
}
