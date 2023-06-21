package com.Chumaengi.chumaengi.member.domain;

import com.Chumaengi.chumaengi.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="member")
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "picture")
    private String picture;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Builder
    public Member(String name, String email, String picture,
                   String password, String nickname) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.password = password;
        this.nickname = nickname;
    }

    public static Member createMember(String name, String email, String picture,
                                      String password, String nickname){
        return new Member(name, email, picture, password, nickname);
    }
}
