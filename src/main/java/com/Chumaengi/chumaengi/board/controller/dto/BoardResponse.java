package com.Chumaengi.chumaengi.board.controller.dto;

import com.Chumaengi.chumaengi.board.domain.Category;
import com.Chumaengi.chumaengi.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String writer;
    private final int view;
    private final LocalDateTime createdDate;
    private final Category category;
    private final Member member;

    @Builder
    public BoardResponse(Long id, String title, String writer, String content,
                         int view, LocalDateTime createdDate, Category category, Member member){
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.view = view;
        this.createdDate = createdDate;
        this.category = category;
        this.member = member;
    }
}
