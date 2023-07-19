package com.Chumaengi.chumaengi.member.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class MemberRequest {
    @NotBlank(message = "수정할 이름을 작성해주세요")
    private String name;

    @NotBlank(message = "수정할 비밀번호를 작성해주세요")
    private String password;

    @NotBlank(message = "수정할 닉네임을 작성해주세요")
    private String nickname;

    @Builder
    public MemberRequest(String name, String password, String nickname){
        this.name = name;
        this.password=password;
        this.nickname=nickname;
    }
}
