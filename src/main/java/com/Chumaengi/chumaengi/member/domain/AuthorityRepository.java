package com.Chumaengi.chumaengi.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    @Query("select a from Authority a where a.member.Id = :memberId")
    List<Authority> findByMemberJpql(@Param("memberId") Long memberId);
}
