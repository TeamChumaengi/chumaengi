package com.Chumaengi.chumaengi.auth.domain;

import com.Chumaengi.chumaengi.auth.exception.AuthErrorCode;
import com.Chumaengi.chumaengi.global.exception.ChumaengiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@DisplayName("Token [Repository Layer] TokenRepository 테스트(redis)")
public class TokenRepositoryTest {
    @Autowired
    private TokenRepository tokenRepository;

    final Long MEMBER_ID = 1L;
    final String REFRESHTOKEN = "chumaengi_refresh_token";
    Token token;

    @BeforeEach
    void setup() {
        token = tokenRepository.save(Token.issueRefreshToken(MEMBER_ID, REFRESHTOKEN, 600));
        System.out.println(token.getRefresh_token());
        System.out.println(tokenRepository.count());
        System.out.println(tokenRepository.findById(MEMBER_ID).get().getRefresh_token());
    }

    @Test
    @DisplayName("회원 ID(=리프레시토큰ID)를 통해서 가지고 있는 RefreshToken을 조회한다")
    void findById() {
        //when
        Token findToken = tokenRepository.findById(MEMBER_ID)
                .orElseThrow(()-> ChumaengiException.type(AuthErrorCode.AUTH_EXPIRED_TOKEN));

        //then
        assertAll(
                () -> assertThat(findToken.getId()).isEqualTo(1L),
                () -> assertThat(findToken.getRefresh_token()).isEqualTo("chumaengi_refresh_token"),
                () -> assertThat(findToken.getExpiration()).isEqualTo(600)
        );
    }

}
