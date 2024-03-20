package com.sparta.njick.domain.card.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.njick.domain.assign.infrastructure.AssignEntity;
import com.sparta.njick.domain.assign.infrastructure.AssignJpaRepository;
import com.sparta.njick.domain.assign.model.Assign;
import com.sparta.njick.domain.card.dto.CardInfoDto;
import com.sparta.njick.domain.card.infrastructure.entity.CardEntity;
import com.sparta.njick.domain.card.infrastructure.entity.CardJpaRepository;
import com.sparta.njick.domain.card.model.Card;
import com.sparta.njick.global.exception.CustomRuntimeException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CardRepositoryImpl implements CardRepository {

    private final CardJpaRepository cardJpaRepository;
    private final AssignJpaRepository assignJpaRepository;
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
    public void assignAll(List<Assign> assigns) {
        List<AssignEntity> entities = assigns.stream()
            .map(it -> AssignEntity.builder()
                .cardId(it.getCardId())
                .userId(it.getUserId())
                .build())
            .toList();
        assignJpaRepository.saveAll(entities);
    }

    @Override
    public List<Assign> findAssignsByCardId(Long cardId) {
        List<AssignEntity> entities = assignJpaRepository.findAllByCardId(cardId);
        return entities.stream()
            .map(AssignEntity::toModel)
            .toList();
    }

    @Override
    public void deleteByTaskStateId(Long stateId) {

    }

    @Override
    public Card get(Long cardId) {
        CardEntity found = findById(cardId);
        return found.toModel();
    }

    private CardEntity findById(Long cardId) {
        return cardJpaRepository.findById(cardId).orElseThrow(
            () -> new CustomRuntimeException("해당 카드를 찾을 수 없습니다.")
        );
    }

}
