package com.Chumaengi.chumaengi.comment.controller.dto;

import com.Chumaengi.chumaengi.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentListResponse {
    private final Long id;
    private final String content;
    private final String writer;
    private final LocalDateTime createdDate;
    private final Member member;

    @Builder
    public CommentListResponse(Long id, String content, String writer,
                               LocalDateTime createdDate, Member member) {
        this.id = id;
        this.content = content;
        this.writer = writer;
        this.createdDate = createdDate;
        this.member = member;
    }
}
