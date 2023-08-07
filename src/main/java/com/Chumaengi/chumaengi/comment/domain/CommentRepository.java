package com.Chumaengi.chumaengi.comment.domain;

import com.Chumaengi.chumaengi.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoard(Board board);
}
