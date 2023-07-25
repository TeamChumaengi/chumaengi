package com.Chumaengi.chumaengi.auth.service;

import com.Chumaengi.chumaengi.auth.controller.dto.TokenResponse;
import com.Chumaengi.chumaengi.auth.domain.Token;
import com.Chumaengi.chumaengi.auth.domain.TokenRepository;
import com.Chumaengi.chumaengi.auth.exception.AuthErrorCode;
import com.Chumaengi.chumaengi.auth.security.JwtProvider;
import com.Chumaengi.chumaengi.global.exception.ChumaengiException;
import com.Chumaengi.chumaengi.member.domain.Member;
import com.Chumaengi.chumaengi.member.service.MemberFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TokenService {
    private final MemberFindService memberFindService;
    private final JwtProvider jwtProvider;
    private final TokenRepository tokenRepository;

    /**
     * Refresh 토큰을 생성한다.
     * Redis 내부
     * refreshToken:memberId : tokenValue 형태로 저장
     */
    public String createRefreshToken(Member member) {

        String refresh_token = UUID.randomUUID().toString();
        Token token = tokenRepository.save(Token.issueRefreshToken(member.getId(), refresh_token, 6000));

        return token.getRefreshToken();
    }

    public Token validRefreshToken(Member member, String refreshToken) throws Exception {
        Token token = tokenRepository.findById(member.getId())
                .orElseThrow(() -> ChumaengiException.type(AuthErrorCode.AUTH_EXPIRED_TOKEN));

        // Redis에 해당 유저의 토큰이 존재하지 않는 경우 (만료된 상태)
        if (token.getRefreshToken() == null) {
            throw ChumaengiException.type(AuthErrorCode.AUTH_EXPIRED_TOKEN);
        } else {
            // refresh 토큰 만료일자가 10초 미만이라면 새로운 refresh 토큰 부여
            if(token.getExpiration() < 10) {
                token.updateRefreshToken(refreshToken);
                tokenRepository.save(Token.issueRefreshToken(member.getId(), refreshToken, 6000));
            }

            // 토큰이 같은지 비교, 다르다면 유효하지 않은 토큰
            if(!token.getRefreshToken().equals(refreshToken)) {
                throw ChumaengiException.type(AuthErrorCode.AUTH_INVALID_TOKEN);
            } else {
                return token;
            }
        }
    }


    public TokenResponse refreshAccessToken(TokenResponse token) throws Exception {
        String email = jwtProvider.getAccount(token.getRefreshToken());
        Member member = memberFindService.findByEmail(email);

        Token refreshToken = validRefreshToken(member, token.getRefreshToken());

        if (refreshToken != null) {
            return TokenResponse.builder()
                    .accessToken(jwtProvider.createToken(email, member.getRoles()))
                    .refreshToken(refreshToken.getRefreshToken())
                    .build();
        } else {
            throw ChumaengiException.type(AuthErrorCode.INVALID_PERMISSION);
        }
    }

}
