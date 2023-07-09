package com.Chumaengi.chumaengi.member.service;

import com.Chumaengi.chumaengi.global.exception.ChumaengiException;
import com.Chumaengi.chumaengi.member.domain.Member;
import com.Chumaengi.chumaengi.member.domain.MemberRepository;
import com.Chumaengi.chumaengi.member.exception.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberFindService {
    private final MemberRepository memberRepository;

    public Member findById(Long userId) {
        return memberRepository.findById(userId)
                .orElseThrow(() -> ChumaengiException.type(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> ChumaengiException.type(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
