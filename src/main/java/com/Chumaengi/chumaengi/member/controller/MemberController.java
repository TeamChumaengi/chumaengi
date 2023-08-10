package com.Chumaengi.chumaengi.member.controller;

import com.Chumaengi.chumaengi.member.controller.dto.MemberResponse;
import com.Chumaengi.chumaengi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/detail/{memberId}")
    public String MemberDetail(@PathVariable Long memberId, Model model){
        MemberResponse memberResponse = memberService.findById(memberId);
        model.addAttribute("member",memberResponse);

        return "user/member_detail";
    }

    @GetMapping("/update/{memberId}")
    public String MemberUpdate(@PathVariable Long memberId, Model model){
        MemberResponse memberResponse = memberService.findById(memberId);
        model.addAttribute("member",memberResponse);

        return "user/member_update";
    }

}
