package com.Chumaengi.chumaengi.auth.service;

import com.Chumaengi.chumaengi.auth.controller.dto.AuthRequest;
import com.Chumaengi.chumaengi.auth.controller.dto.AuthResponse;
import com.Chumaengi.chumaengi.auth.exception.AuthErrorCode;
import com.Chumaengi.chumaengi.auth.security.JwtProvider;
import com.Chumaengi.chumaengi.common.exception.ChumaengiException;
import com.Chumaengi.chumaengi.member.domain.Authority;
import com.Chumaengi.chumaengi.member.domain.Member;
import com.Chumaengi.chumaengi.member.domain.MemberRepository;
import com.Chumaengi.chumaengi.member.service.MemberFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final MemberFindService memberFindService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public Long signup(AuthRequest request) {
        validDuplicateUserByEmail(request.getEmail());

        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .nickname(request.getNickname())
                .build();

        member.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));

        return memberRepository.save(member).getId();
    }

    private void validDuplicateUserByEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw ChumaengiException.type(AuthErrorCode.DUPLICATE_EMAIL);
        }
    }

    public AuthResponse login(AuthRequest request) {
        validatePassword(request.getPassword(), request.getPassword());

        Member member = memberFindService.findByEmail(request.getEmail());

        return AuthResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .nickname(member.getNickname())
                .roles(member.getRoles())
                .token(jwtProvider.createToken(member.getEmail(), member.getRoles()))
                .build();

    }

    private void validatePassword(String encodedPassword, String rawPassword) {
        if (!PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(rawPassword, encodedPassword)) {
            throw ChumaengiException.type(AuthErrorCode.WRONG_PASSWORD);
        }
    }

    public AuthResponse getMember(String email) {
        Member member = memberFindService.findByEmail(email);

        return new AuthResponse(member);
    }

}
