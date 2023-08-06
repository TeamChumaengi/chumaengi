package com.Chumaengi.chumaengi.board.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardListResponse {
    private final Long id;
    private final String title;
    private final String writer;
    private final int view;
    private final LocalDateTime createdDate;

    @Builder
    public BoardListResponse(Long id, String title, String writer, int view,
                             LocalDateTime createdDate){
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.view = view;
        this.createdDate = createdDate;
    }
}
