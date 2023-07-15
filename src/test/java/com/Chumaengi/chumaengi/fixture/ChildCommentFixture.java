package com.Chumaengi.chumaengi.fixture;

import com.Chumaengi.chumaengi.board.domain.Board;
import com.Chumaengi.chumaengi.comment.domain.Comment;
import com.Chumaengi.chumaengi.member.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChildCommentFixture {
    CHILD_COMMENT_0("대댓글0"),
    CHILD_COMMENT_1("대댓글1"),
    CHILD_COMMENT_2("대댓글2"),
    CHILD_COMMENT_3("대댓글3")
    ;

    private final String content;

    public Comment toChildComment(Member writer, Board board, Comment parent) {
        return Comment.createComment(writer, board, parent, content);
    }
}
