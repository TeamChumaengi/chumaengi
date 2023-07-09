package com.Chumaengi.chumaengi.auth.controller;

import com.Chumaengi.chumaengi.auth.controller.dto.TokenResponse;
import com.Chumaengi.chumaengi.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenReissueApiController {
    private final TokenService tokenService;

    @GetMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestBody TokenResponse token) throws Exception {
        return new ResponseEntity<>(tokenService.refreshAccessToken(token), HttpStatus.OK);
    }
}
