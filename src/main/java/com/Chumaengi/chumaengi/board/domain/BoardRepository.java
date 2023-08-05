package com.Chumaengi.chumaengi.board.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select b from Board b where b.category = :category order by b.createdDate desc ")
    Page<Board> findByCategory(@Param("category") Category category, Pageable pageable);
}
