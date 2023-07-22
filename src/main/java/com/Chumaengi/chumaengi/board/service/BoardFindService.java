package com.Chumaengi.chumaengi.board.service;

import com.Chumaengi.chumaengi.board.domain.Board;
import com.Chumaengi.chumaengi.board.domain.BoardRepository;
import com.Chumaengi.chumaengi.board.exception.BoardErrorCode;
import com.Chumaengi.chumaengi.global.exception.ChumaengiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardFindService {
    private final BoardRepository boardRepository;

    @Transactional
    public Board findById(Long boardId){
        return boardRepository.findById(boardId)
                .orElseThrow(() -> ChumaengiException.type(BoardErrorCode.BOARD_NOT_FOUND));
    }
}
