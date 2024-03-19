package com.sparta.njick.domain.card.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.njick.domain.card.infrastructure.entity.CardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CardRepositoryImpl implements CardRepository {

    private final CardJpaRepository cardJpaRepository;
    private final JPAQueryFactory queryFactory;
}
