package com.Chumaengi.chumaengi.comment.service;

import com.Chumaengi.chumaengi.board.domain.Board;
import com.Chumaengi.chumaengi.board.service.BoardFindService;
import com.Chumaengi.chumaengi.comment.domain.Comment;
import com.Chumaengi.chumaengi.comment.domain.CommentRepository;
import com.Chumaengi.chumaengi.comment.exception.CommentErrorCode;
import com.Chumaengi.chumaengi.global.exception.ChumaengiException;
import com.Chumaengi.chumaengi.member.domain.Member;
import com.Chumaengi.chumaengi.member.service.MemberFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final MemberFindService memberFindService;
    private final BoardFindService boardFindService;
    private final CommentFindService commentFindService;
    private final CommentRepository commentRepository;

    @Transactional
    public Long create(Long writerId, Long boardId, String content){
        Member writer = memberFindService.findById(writerId);
        Board board = boardFindService.findById(boardId);
        Comment comment = Comment.createComment(writer, board, null, content);

        return commentRepository.save(comment).getId();
    }

    @Transactional
    public void delete(Long writerId, Long commentId){
        validateWriter(commentId, writerId);
        commentRepository.deleteById(commentId);
    }

    private void validateWriter(Long commentId, Long writerId) {
        Comment comment = commentFindService.findById(commentId);
        if (!comment.getWriter().getId().equals(writerId)) {
            throw ChumaengiException.type(CommentErrorCode.USER_IS_NOT_COMMENT_WRITER);
        }
    }
}

