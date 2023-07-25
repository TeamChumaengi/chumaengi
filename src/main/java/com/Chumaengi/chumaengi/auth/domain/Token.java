package com.Chumaengi.chumaengi.auth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.concurrent.TimeUnit;

@Getter
@RedisHash("refreshToken") //스프링 데이터를 이용해 객체를 redis에 저장하기 위해 사용한다.
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Id
    @JsonIgnore
    private Long id;

    private String refreshToken;

    @TimeToLive(unit = TimeUnit.SECONDS)
    private Integer expiration;

    public static Token issueRefreshToken(Long id, String refreshToken, Integer expiration) {
        return new Token(id, refreshToken, expiration);
    }

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
}