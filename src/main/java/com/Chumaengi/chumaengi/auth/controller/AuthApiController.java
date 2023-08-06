package com.Chumaengi.chumaengi.auth.controller;

import com.Chumaengi.chumaengi.auth.controller.dto.AuthRequest;
import com.Chumaengi.chumaengi.auth.controller.dto.AuthResponse;
import com.Chumaengi.chumaengi.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.Chumaengi.chumaengi.auth.controller.AuthApiController.SessionConst.*;

@RestController
@RequiredArgsConstructor
public class AuthApiController {
    private final AuthService authService;
    public interface SessionConst {
        String LOGIN_ID = "id";
        String LOGIN_USERID = "userid";
        String LOGIN_USER_ROLE = "role";
    }

    @PostMapping("/users/signup")
    public ResponseEntity<Boolean> signup(@RequestBody AuthRequest request) {
        authService.signup(request);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/users/login")
    public ResponseEntity<Boolean> login(@RequestBody AuthRequest request, HttpSession httpSession) {
        AuthResponse authResponse = authService.login(request);
        String role = authService.searchMemberRole(authResponse.getEmail());
        httpSession.setAttribute(LOGIN_ID, authResponse.getId());
        httpSession.setAttribute(LOGIN_USERID, authResponse.getEmail());
        httpSession.setAttribute(LOGIN_USER_ROLE, role);
        System.out.println(role);

        return ResponseEntity.ok(true);
    }
}
