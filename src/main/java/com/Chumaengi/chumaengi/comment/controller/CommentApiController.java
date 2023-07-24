package com.Chumaengi.chumaengi.comment.controller;

import com.Chumaengi.chumaengi.comment.controller.dto.CommentRequest;
import com.Chumaengi.chumaengi.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments/{writerId}")
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/{boardId}")
    public ResponseEntity<Void> create(@PathVariable Long writerId, @PathVariable Long boardId,
                                       @RequestBody @Valid CommentRequest request) {
        commentService.create(writerId, boardId, request.getContent());
        return ResponseEntity.created(URI.create("/boards/detail/" + boardId)).build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long writerId, @PathVariable Long commentId) {
        commentService.delete(writerId, commentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{boardId}/{parentId}/child-comments")
    public ResponseEntity<Void> createChild(@PathVariable Long writerId, @PathVariable Long boardId,
                                            @PathVariable Long parentId, @RequestBody @Valid CommentRequest request) {
        commentService.createChild(writerId, boardId, parentId, request.getContent());
        return ResponseEntity.created(URI.create("/board/detail/" + boardId)).build();
    }
}

