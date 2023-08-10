package com.Chumaengi.chumaengi.member.controller;

import com.Chumaengi.chumaengi.member.controller.dto.MemberRequest;
import com.Chumaengi.chumaengi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.Chumaengi.chumaengi.auth.controller.AuthApiController.SessionConst.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{memberId}")
public class MemberApiController {
    private final MemberService memberService;

    @PatchMapping
    public ResponseEntity<Boolean> update(@PathVariable Long memberId,
                                               @RequestBody @Valid MemberRequest request) {
        memberService.update(memberId, request.getName(), request.getPassword(), request.getNickname());
        return ResponseEntity.ok(true);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@PathVariable Long memberId, HttpSession httpSession) {
        memberService.delete(memberId);
        httpSession.setAttribute(LOGIN_ID, null);
        httpSession.setAttribute(LOGIN_USERID, null);
        httpSession.setAttribute(LOGIN_USER_ROLE, null);
        return ResponseEntity.ok(true);
    }
}
