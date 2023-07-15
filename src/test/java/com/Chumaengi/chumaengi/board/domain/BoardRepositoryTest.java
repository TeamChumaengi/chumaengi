package com.Chumaengi.chumaengi.board.domain;

import com.Chumaengi.chumaengi.comment.domain.CommentRepository;
import com.Chumaengi.chumaengi.global.config.JpaAuditingConfig;
import com.Chumaengi.chumaengi.member.domain.Member;
import com.Chumaengi.chumaengi.member.domain.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;

import static com.Chumaengi.chumaengi.board.domain.Category.Question;
import static com.Chumaengi.chumaengi.fixture.BoardFixture.BOARD_0;
import static com.Chumaengi.chumaengi.fixture.MemberFixture.SUNKYOUNG;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@Import(JpaAuditingConfig.class)
@DisplayName("Board [Repository Layer] BoardRepository 테스트")
public class BoardRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("게시글을 생성한다")
    void saveBoard() {
        //given
        Member writer = memberRepository.save(SUNKYOUNG.toMember());
        boardRepository.save(BOARD_0.toBoard(writer));
        LocalDateTime now = LocalDateTime.of(2023,6,21,0,0,0);

        //when
        List<Board> boardList = boardRepository.findAll();

        //then
        Board board = boardList.get(0);

        assertAll(
                () -> assertThat(board.getWriter()).isEqualTo(writer),
                () -> assertThat(board.getTitle()).isEqualTo(BOARD_0.getTitle()),
                () -> assertThat(board.getContent()).isEqualTo(BOARD_0.getContent()),
                () -> assertThat(board.getCategory()).isEqualTo(Question),
                () -> assertThat(board.getCreatedDate()).isAfter(now),
                () -> assertThat(board.getModifiedDate()).isAfter(now)
        );
    }
}
