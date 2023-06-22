package com.Chumaengi.chumaengi.auth.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String email;

    private String password;

    private String nickname;

    private String name;

    @Builder
    public AuthRequest(String email, String password, String nickname, String name){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
    }
}
