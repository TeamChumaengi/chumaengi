package com.Chumaengi.chumaengi.board.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class BoardRequest {
    @NotBlank(message = "제목을 작성해주세요")
    private String title;

    @NotBlank(message = "내용을 작성해주세요")
    private String content;

    @NotBlank(message = "카테고리를 선택해주세요")
    private String category;

    @Builder
    public BoardRequest(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
