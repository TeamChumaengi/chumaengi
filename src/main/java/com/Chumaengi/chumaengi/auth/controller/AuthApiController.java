package com.Chumaengi.chumaengi.auth.controller;

import com.Chumaengi.chumaengi.auth.controller.dto.AuthRequest;
import com.Chumaengi.chumaengi.auth.controller.dto.AuthResponse;
import com.Chumaengi.chumaengi.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthApiController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Boolean> signup(@RequestBody AuthRequest request) {
        authService.signup(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authService.login(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/get")
    public ResponseEntity<AuthResponse> getUser(@RequestParam String email) {
        authService.getMember(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin/get")
    public ResponseEntity<AuthResponse> getUserForAdmin(@RequestParam String email) {
        authService.getMember(email);
        return ResponseEntity.ok().build();
    }
}
