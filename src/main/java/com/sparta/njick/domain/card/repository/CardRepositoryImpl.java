package com.sparta.njick.domain.card.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.njick.domain.card.infrastructure.entity.CardEntity;
import com.sparta.njick.domain.card.infrastructure.entity.CardJpaRepository;
import com.sparta.njick.domain.card.model.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CardRepositoryImpl implements CardRepository {

    private final CardJpaRepository cardJpaRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    public Card save(Card model) {
        CardEntity entity = CardEntity.builder()
            .title(model.getTitle())
            .description(model.getDescription())
            .cardColor(model.getCardColor())
            .deadline(model.getDeadline())
            .boardId(model.getBoardId())
            .taskStateId(model.getTaskStateId())
            .creatorId(model.getCreatorId())
            .build();
        return cardJpaRepository.save(entity).toModel();
    }
}
