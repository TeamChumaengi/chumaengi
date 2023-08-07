package com.Chumaengi.chumaengi.comment.service;

import com.Chumaengi.chumaengi.board.domain.Board;
import com.Chumaengi.chumaengi.board.service.BoardFindService;
import com.Chumaengi.chumaengi.comment.controller.dto.CommentListResponse;
import com.Chumaengi.chumaengi.comment.domain.Comment;
import com.Chumaengi.chumaengi.comment.domain.CommentRepository;
import com.Chumaengi.chumaengi.comment.exception.CommentErrorCode;
import com.Chumaengi.chumaengi.global.exception.ChumaengiException;
import com.Chumaengi.chumaengi.member.domain.Member;
import com.Chumaengi.chumaengi.member.service.MemberFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public Long createChild(Long writerId, Long boardId, Long parentId, String content){
        Member writer = memberFindService.findById(writerId);
        Board board = boardFindService.findById(boardId);
        Comment parentComment = commentFindService.findById(parentId);
        Comment childComment = Comment.createComment(writer, board, parentComment, content);

        return commentRepository.save(childComment).getId();
    }

    @Transactional
    public List<CommentListResponse> findByBoard(Board board){
        List<Comment> comments = commentRepository.findByBoard(board);
        List<CommentListResponse> commentList = comments.stream().map(m -> CommentListResponse.builder()
                .id(m.getId())
                .content(m.getContent())
                .writer(m.getWriter().getNickname())
                .createdDate(m.getCreatedDate())
                .member(m.getWriter())
                .board(m.getBoard())
                .build())
                .collect(Collectors.toList());
        return commentList;
    }

    private void validateWriter(Long commentId, Long writerId) {
        Comment comment = commentFindService.findById(commentId);
        if (!comment.getWriter().getId().equals(writerId)) {
            throw ChumaengiException.type(CommentErrorCode.USER_IS_NOT_COMMENT_WRITER);
        }
    }
}

