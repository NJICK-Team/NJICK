package com.sparta.njick.domain.card.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.njick.domain.assign.infrastructure.AssignEntity;
import com.sparta.njick.domain.assign.infrastructure.AssignJpaRepository;
import com.sparta.njick.domain.assign.model.Assign;
import com.sparta.njick.domain.card.dto.request.CardCreateRequestDto;
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
    public Card save(CardCreateRequestDto requestDto, Long boardId, Long userId) {
        CardEntity entity = CardEntity.builder()
            .title(requestDto.getTitle())
            .description(requestDto.getDescription())
            .cardColor(requestDto.getCardColor())
            .deadline(requestDto.getDeadline())
            .boardId(boardId)
            .taskStateId(requestDto.getTaskStateId())
            .creatorId(userId)
            .build();
        return cardJpaRepository.save(entity).toModel();
    }

    @Override
    public List<Assign> assignAll(List<Long> assignedUserIds, Long cardId) {
        List<AssignEntity> entities = assignedUserIds.stream()
            .map(assignedUserId -> AssignEntity.builder()
                .cardId(cardId)
                .userId(assignedUserId)
                .build())
            .toList();
        return assignJpaRepository.saveAll(entities).stream()
            .map(AssignEntity::toModel)
            .toList();
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
