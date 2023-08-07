package com.Chumaengi.chumaengi.board.controller;

import com.Chumaengi.chumaengi.board.controller.dto.BoardRequest;
import com.Chumaengi.chumaengi.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{writerId}")
public class BoardApiController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Boolean> create(@PathVariable Long writerId,
                                       @RequestBody @Valid BoardRequest request) {
        System.out.println(request.getTitle()+" "+request.getContent()+" "+request.getCategory());
        boardService.create(writerId, request.getTitle(), request.getContent(), request.getCategory());
        return ResponseEntity.ok(true);
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity<Boolean> update(@PathVariable Long writerId, @PathVariable Long boardId,
                                       @RequestBody @Valid BoardRequest request) {
        boardService.update(writerId, boardId, request.getTitle(), request.getContent(), request.getCategory());
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> delete(@PathVariable Long writerId, @PathVariable Long boardId) {
        boardService.delete(writerId, boardId);
        return ResponseEntity.ok().build();
    }
}