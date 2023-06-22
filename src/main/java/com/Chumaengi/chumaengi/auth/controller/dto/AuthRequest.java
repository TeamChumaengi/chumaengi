package com.Chumaengi.chumaengi.auth.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String name;

    private String email;

    private String password;

    private String nickname;

    @Builder
    public AuthRequest(String name, String email, String password, String nickname){
        this.name = name;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
