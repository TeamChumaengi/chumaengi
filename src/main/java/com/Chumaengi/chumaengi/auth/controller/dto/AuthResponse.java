package com.Chumaengi.chumaengi.auth.controller.dto;

import com.Chumaengi.chumaengi.member.domain.Authority;
import com.Chumaengi.chumaengi.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private Long id;

    private String name;

    private String email;

    private String nickname;

    private List<Authority> roles = new ArrayList<>();

    private String token;

    public AuthResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.roles = member.getRoles();
    }
}
