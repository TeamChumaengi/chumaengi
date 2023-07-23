package com.Chumaengi.chumaengi.comment.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CommentRequest {
    @NotBlank(message = "내용을 작성해주세요")
    private String content;

    @Builder
    public CommentRequest(String content) {
        this.content = content;
    }
}
