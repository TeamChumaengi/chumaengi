package com.Chumaengi.chumaengi.fixture;

import com.Chumaengi.chumaengi.member.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberFixture {
    SUNKYOUNG("윤선경", "qwe1234@gmail.com", "qwe1234", "추맹이1"),
    HOEUN("이호은", "qwe5678@gmail.com", "qwe5678", "추맹이2"),
    YUJIN("소유진", "asd1234@gmail.com", "asd1234", "추맹이3"),
    HEESUN("박희선", "asd5678@gmail.com", "asd5678", "추맹이4")
    ;

    private final String name;
    private final String email;
    private final String password;
    private final String nickname;

    public Member toMember() { return Member.createMember(name, email, password, nickname); }
}
