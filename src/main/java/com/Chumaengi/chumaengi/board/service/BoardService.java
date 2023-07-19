package com.Chumaengi.chumaengi.board.service;

import com.Chumaengi.chumaengi.board.domain.Board;
import com.Chumaengi.chumaengi.board.domain.BoardRepository;
import com.Chumaengi.chumaengi.board.exception.BoardErrorCode;
import com.Chumaengi.chumaengi.global.exception.ChumaengiException;
import com.Chumaengi.chumaengi.member.domain.Member;
import com.Chumaengi.chumaengi.member.service.MemberFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final MemberFindService memberFindService;
    private final BoardFindService boardFindService;
    private final BoardRepository boardRepository;

    @Transactional
    public Long create(Long writerId, String title, String content, String category){
        Member writer = memberFindService.findById(writerId);
        Board board = Board.createBoard(writer, title, content, category);

        return boardRepository.save(board).getId();
    }

    @Transactional
    public void update(Long writerId, Long boardId, String title, String content, String category){
        validateWriter(boardId, writerId);
        Board board = boardFindService.findById(boardId);

        board.update(title, content, category);
    }

    @Transactional
    public void delete(Long writerId, Long boardId){
        validateWriter(boardId, writerId);
        boardRepository.deleteById(boardId);
    }

    private void validateWriter(Long boardId, Long writerId) {
        Board board = boardFindService.findById(boardId);
        if (!board.getWriter().getId().equals(writerId)) {
            throw ChumaengiException.type(BoardErrorCode.USER_IS_NOT_BOARD_WRITER);
        }
    }
}

