package com.Chumaengi.chumaengi.member.controller;

import com.Chumaengi.chumaengi.member.controller.dto.MemberRequest;
import com.Chumaengi.chumaengi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/{memberId}")
public class MemberApiController {
    private final MemberService memberService;

    @PatchMapping
    public ResponseEntity<Void> update(@PathVariable Long memberId,
                                               @RequestBody @Valid MemberRequest request) {
        memberService.update(memberId, request.getName(), request.getPassword(), request.getNickname());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long memberId) {
        memberService.delete(memberId);
        return ResponseEntity.ok().build();
    }
}
