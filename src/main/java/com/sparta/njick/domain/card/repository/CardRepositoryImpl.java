package com.sparta.njick.domain.card.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.njick.domain.card.dto.CardInfoDto;
import com.sparta.njick.domain.card.infrastructure.entity.CardEntity;
import com.sparta.njick.domain.card.infrastructure.entity.CardJpaRepository;
import com.sparta.njick.domain.card.model.Card;
import com.sparta.njick.global.exception.CustomRuntimeException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CardRepositoryImpl implements CardRepository {

    private final CardJpaRepository cardJpaRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    public CardInfoDto save(Card model) {
        CardEntity entity = CardEntity.builder()
            .title(model.getTitle())
            .description(model.getDescription())
            .cardColor(model.getCardColor())
            .deadline(model.getDeadline())
            .boardId(model.getBoardId())
            .taskStateId(model.getTaskStateId())
            .creatorId(model.getCreatorId())
            .build();
        CardEntity saved = cardJpaRepository.save(entity);
        return new CardInfoDto(saved);
    }

    @Override
    public Card get(Long boardId, Long cardId) {
        CardEntity found = findById(cardId);
        validateBoardId(found.getBoardId(), boardId);

        return found.toModel();
    }

    private CardEntity findById(Long cardId) {
        return cardJpaRepository.findById(cardId).orElseThrow(
            () -> new CustomRuntimeException("해당 카드를 찾을 수 없습니다.")
        );
    }

    private void validateBoardId(Long origin, Long input) {
        if (!Objects.equals(origin, input)) {
            throw new CustomRuntimeException("해당 보드에 맞는 카드가 아닙니다.");
        }
    }
}
