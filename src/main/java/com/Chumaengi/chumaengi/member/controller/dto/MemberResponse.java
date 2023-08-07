package com.Chumaengi.chumaengi.member.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {
    private final Long id;
    private final String name;
    private final String email;
    private final String password;
    private final String nickname;

    @Builder
    public MemberResponse(Long id, String name, String email, String password,
                          String nickname) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
