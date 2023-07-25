package com.Chumaengi.chumaengi.auth.controller;

import com.Chumaengi.chumaengi.auth.controller.dto.AuthRequest;
import com.Chumaengi.chumaengi.auth.controller.dto.AuthResponse;
import com.Chumaengi.chumaengi.auth.service.AuthService;
import com.Chumaengi.chumaengi.global.exception.ChumaengiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthApiController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Boolean> signup(@RequestBody AuthRequest request) {
        try{
            authService.signup(request);
        } catch (ChumaengiException e){
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    @CrossOrigin(origins = "http://localhost:8080", exposedHeaders = "Authorization")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        AuthResponse authResponse = null;
        try{
            authResponse = authService.login(request);
        } catch (ChumaengiException e){
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(authResponse.getToken().getAccessToken());
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
