package com.Chumaengi.chumaengi.auth.domain;

import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, Long> {
}