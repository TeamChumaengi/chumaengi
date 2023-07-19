package com.Chumaengi.chumaengi.member.service;

import com.Chumaengi.chumaengi.member.domain.Member;
import com.Chumaengi.chumaengi.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberFindService memberFindService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void update(Long memberId, String name, String password, String nickname) {
        Member member = memberFindService.findById(memberId);

        member.update(name, passwordEncoder.encode(password), nickname);
    }

    @Transactional
    public void delete(Long memberId) {
        Member member = memberFindService.findById(memberId);

        memberRepository.delete(member);
    }
}
