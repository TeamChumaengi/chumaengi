package com.Chumaengi.chumaengi.global.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class PageController {
    @RequestMapping(value = "/")
    public String mainView() {
        return "user/index";
    }

    //회원등록페이지로 이동
    @GetMapping("/user/signup")
    public String signup() {
        return "user/signup";
    }

    //로그인 페이지로 이동
    @GetMapping("/user/login")
    public String login() {
        return "user/login";
    }

    //로그아웃후 메인페이지로 이동
    //로그아웃
    @RequestMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "user/index";

    }
}
