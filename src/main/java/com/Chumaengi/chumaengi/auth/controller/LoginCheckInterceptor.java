package com.Chumaengi.chumaengi.auth.controller;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// 인터셉터 클래스는 HandlerInterceptor 를 속한 메서드를 쓰기 위해 implements
public class LoginCheckInterceptor implements HandlerInterceptor {
    public interface SessionConst {
        String LOGIN_USERID = "userid";
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, IOException {
        // 요청한 주소
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        HttpSession session = request.getSession();
        // 세션에 로그인 정보가 있는지 확인
        if (session.getAttribute(SessionConst.LOGIN_USERID) == null) {
            System.out.println("세션 로그인 null");
            // 로그인을 하지 않은 경우 로그인페이지로 이동
            session.setAttribute("redirectURL", requestURI);
            response.sendRedirect("/user/login");
            System.out.println("세션 로그인 null2");
            return false;
        } else {
            System.out.println("세션 로그인 상태");
            // 로그인 상태
            return true;
        }
    }
}