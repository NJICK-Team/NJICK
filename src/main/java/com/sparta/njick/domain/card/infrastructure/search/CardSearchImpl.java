package com.sparta.njick.domain.card.infrastructure.search;

import static com.sparta.njick.domain.card.infrastructure.entity.QCardEntity.cardEntity;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.njick.domain.card.infrastructure.entity.CardEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CardSearchImpl implements CardSearch {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CardEntity> getAllSortedCards(Pageable pageable, Long boardId) {
        List<CardEntity> fetch = queryFactory.selectFrom(cardEntity)
            .where(cardEntity.boardId.eq(boardId))
            .orderBy(cardSort(pageable.getSort()))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        long totalSize = queryFactory.select(Wildcard.count)
            .from(cardEntity)
            .where(cardEntity.boardId.eq(boardId))
            .fetch()
            .get(0);
        return PageableExecutionUtils.getPage(fetch, pageable, () -> totalSize);
    }

    private OrderSpecifier<?> cardSort(Sort sort) {
        if (sort == null) {
            return cardEntity.id.asc();
        }

        Sort.Order order = sort.iterator().next();
        String property = order.getProperty();
        Sort.Direction direction = order.getDirection();
        return switch (property) {
            case "title" ->
                direction.isAscending() ? cardEntity.title.asc() : cardEntity.title.desc();
            case "deadline" ->
                direction.isAscending() ? cardEntity.deadline.asc() : cardEntity.deadline.desc();
            default -> cardEntity.id.asc();
        };
    }
}
