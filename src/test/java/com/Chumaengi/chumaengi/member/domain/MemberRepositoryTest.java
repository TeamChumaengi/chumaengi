package com.Chumaengi.chumaengi.member.domain;

import com.Chumaengi.chumaengi.global.config.JpaAuditingConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;

import static com.Chumaengi.chumaengi.fixture.MemberFixture.SUNKYOUNG;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@Import(JpaAuditingConfig.class)
@DisplayName("Member [Repository Layer] MemberRepository 테스트")
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원을 저장한다")
    void saveMember() {
        //given
        memberRepository.save(SUNKYOUNG.toMember());
        LocalDateTime now = LocalDateTime.of(2023,6,21,0,0,0);

        //when
        List<Member> memberList = memberRepository.findAll();

        //then
        Member member = memberList.get(0);
        assertAll(
                () -> assertThat(member.getName()).isEqualTo(SUNKYOUNG.getName()),
                () -> assertThat(member.getEmail()).isEqualTo(SUNKYOUNG.getEmail()),
                () -> assertThat(member.getPassword()).isEqualTo(SUNKYOUNG.getPassword()),
                () -> assertThat(member.getNickname()).isEqualTo(SUNKYOUNG.getNickname()),
                () -> assertThat(member.getCreatedDate()).isAfter(now),
                () -> assertThat(member.getModifiedDate()).isAfter(now)
        );
    }
}
