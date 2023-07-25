package com.Chumaengi.chumaengi.auth.service;

import com.Chumaengi.chumaengi.auth.controller.dto.AuthRequest;
import com.Chumaengi.chumaengi.auth.controller.dto.AuthResponse;
import com.Chumaengi.chumaengi.auth.controller.dto.TokenResponse;
import com.Chumaengi.chumaengi.auth.exception.AuthErrorCode;
import com.Chumaengi.chumaengi.auth.security.JwtProvider;
import com.Chumaengi.chumaengi.global.exception.ChumaengiException;
import com.Chumaengi.chumaengi.member.domain.Authority;
import com.Chumaengi.chumaengi.member.domain.Member;
import com.Chumaengi.chumaengi.member.domain.MemberRepository;
import com.Chumaengi.chumaengi.member.service.MemberFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final MemberFindService memberFindService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final TokenService tokenService;

    @Transactional
    public boolean signup(AuthRequest request) {
        validDuplicateMemberByEmail(request.getEmail());
        validDuplicateMemberByNickname(request.getNickname());

        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .nickname(request.getNickname())
                .build();

        member.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));

        memberRepository.save(member);

        return true;
    }

    private void validDuplicateMemberByEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw ChumaengiException.type(AuthErrorCode.DUPLICATE_EMAIL);
        }
    }

    private void validDuplicateMemberByNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw ChumaengiException.type(AuthErrorCode.DUPLICATE_NICKNAME);
        }
    }

    @Transactional
    public AuthResponse login(AuthRequest request) {
        Member member = memberFindService.findByEmail(request.getEmail());
        validatePassword(member.getPassword(), request.getPassword());

        // 로그인 성공 후 refreshToken 발급
        member.setRefreshToken(tokenService.createRefreshToken(member));
        return AuthResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .nickname(member.getNickname())
                .roles(member.getRoles())
                .token(TokenResponse.builder()
                        .accessToken(jwtProvider.createToken(member.getEmail(), member.getRoles()))
                        .refreshToken(member.getRefreshToken())
                        .build())
                .build();

    }

    private void validatePassword(String encodedPassword, String rawPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw ChumaengiException.type(AuthErrorCode.WRONG_PASSWORD);
        }
    }

    public AuthResponse getMember(String email) {
        Member member = memberFindService.findByEmail(email);
        return new AuthResponse(member);
    }
}

