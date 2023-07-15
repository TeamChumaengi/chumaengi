package com.Chumaengi.chumaengi.comment.domain;

import com.Chumaengi.chumaengi.board.domain.Board;
import com.Chumaengi.chumaengi.board.domain.BoardRepository;
import com.Chumaengi.chumaengi.global.config.JpaAuditingConfig;
import com.Chumaengi.chumaengi.member.domain.Member;
import com.Chumaengi.chumaengi.member.domain.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;

import static com.Chumaengi.chumaengi.fixture.BoardFixture.BOARD_0;
import static com.Chumaengi.chumaengi.fixture.ChildCommentFixture.*;
import static com.Chumaengi.chumaengi.fixture.CommentFixture.COMMENT_0;
import static com.Chumaengi.chumaengi.fixture.MemberFixture.SUNKYOUNG;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@Import(JpaAuditingConfig.class)
@DisplayName("Comment [Repository Layer] CommentRepository 테스트")
public class CommentRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    private Member writer;
    private Board board;
    private Comment parentComment;

    @BeforeEach
    void setUp() {
        writer = memberRepository.save(SUNKYOUNG.toMember());
        board = boardRepository.save(BOARD_0.toBoard(writer));
        parentComment = commentRepository.save(COMMENT_0.toComment(writer, board));
    }

    @Test
    @DisplayName("댓글을 생성한다")
    void saveComment() {
        //given
        LocalDateTime now = LocalDateTime.of(2023,6,21,0,0,0);

        //when
        List<Comment> commentList = commentRepository.findAll();

        //then
        Comment comment = commentList.get(0);

        assertAll(
                () -> assertThat(comment.getWriter()).isEqualTo(writer),
                () -> assertThat(comment.getBoard()).isEqualTo(board),
                () -> assertThat(comment.getContent()).isEqualTo(COMMENT_0.getContent()),
                () -> assertThat(comment.getParent()).isEqualTo(null),
                () -> assertThat(comment.getCreatedDate()).isAfter(now),
                () -> assertThat(comment.getModifiedDate()).isAfter(now)
        );
    }

    @Test
    @DisplayName("대댓글을 생성한다")
    void saveChildComment() {
        //given
        commentRepository.save(CHILD_COMMENT_0.toChildComment(writer, board, parentComment));
        LocalDateTime now = LocalDateTime.of(2023, 6, 21, 0, 0, 0);

        //when
        List<Comment> commentList = commentRepository.findAll();

        //then
        Comment childComment = commentList.get(1);

        assertAll(
                () -> assertThat(childComment.getWriter()).isEqualTo(writer),
                () -> assertThat(childComment.getBoard()).isEqualTo(board),
                () -> assertThat(childComment.getContent()).isEqualTo(CHILD_COMMENT_0.getContent()),
                () -> assertThat(childComment.getParent()).isEqualTo(parentComment),
                () -> assertThat(childComment.getCreatedDate()).isAfter(now),
                () -> assertThat(childComment.getModifiedDate()).isAfter(now)
        );
    }
}
