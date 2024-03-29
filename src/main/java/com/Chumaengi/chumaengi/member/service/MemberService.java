package com.Chumaengi.chumaengi.member.service;

import com.Chumaengi.chumaengi.member.controller.dto.MemberResponse;
import com.Chumaengi.chumaengi.member.domain.Member;
import com.Chumaengi.chumaengi.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberFindService memberFindService;

    @Transactional
    public void update(Long memberId, String name, String password, String nickname) {
        Member member = memberFindService.findById(memberId);

        member.update(name, password, nickname);
    }

    @Transactional
    public void delete(Long memberId) {
        Member member = memberFindService.findById(memberId);

        memberRepository.delete(member);
    }

    @Transactional
    public MemberResponse findById(Long memberId){
        Member member = memberFindService.findById(memberId);
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .build();
    }
}
