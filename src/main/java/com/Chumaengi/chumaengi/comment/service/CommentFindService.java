package com.Chumaengi.chumaengi.comment.service;

import com.Chumaengi.chumaengi.comment.domain.Comment;
import com.Chumaengi.chumaengi.comment.domain.CommentRepository;
import com.Chumaengi.chumaengi.comment.exception.CommentErrorCode;
import com.Chumaengi.chumaengi.global.exception.ChumaengiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentFindService {
    private final CommentRepository commentRepository;

    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> ChumaengiException.type(CommentErrorCode.COMMENT_NOT_FOUND));
    }
}
