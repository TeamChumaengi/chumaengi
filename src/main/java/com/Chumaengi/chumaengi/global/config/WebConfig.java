package com.Chumaengi.chumaengi.global.config;

import com.Chumaengi.chumaengi.auth.controller.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 로그인 여부에 따른 접속 가능 페이지 구분
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                // 현재 프로젝트의 모든 주소에 대해 인터셉터를 적용
                .excludePathPatterns("/", "/user/signup","/user/login",
                        "/users/signup","/users/login",
                        "/img/**","/user/logout" ,"/css/**","/js/**","/api/**");
    }

}