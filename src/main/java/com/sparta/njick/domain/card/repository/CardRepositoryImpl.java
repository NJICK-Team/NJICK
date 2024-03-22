package com.sparta.njick.domain.card.repository;

import com.sparta.njick.domain.assign.infrastructure.AssignEntity;
import com.sparta.njick.domain.assign.infrastructure.AssignJpaRepository;
import com.sparta.njick.domain.assign.model.Assign;
import com.sparta.njick.domain.assign.model.Assigns;
import com.sparta.njick.domain.card.dto.request.CardCreateRequestDto;
import com.sparta.njick.domain.card.infrastructure.entity.CardEntity;
import com.sparta.njick.domain.card.infrastructure.entity.CardJpaRepository;
import com.sparta.njick.domain.card.model.Card;
import com.sparta.njick.global.exception.CustomRuntimeException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CardRepositoryImpl implements CardRepository {

    private final CardJpaRepository cardJpaRepository;
    private final AssignJpaRepository assignJpaRepository;

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
    public Assigns assignAll(List<Long> assignedUserIds, Long cardId) {
        List<AssignEntity> entities = assignedUserIds.stream()
            .map(assignedUserId -> AssignEntity.builder()
                .cardId(cardId)
                .userId(assignedUserId)
                .build())
            .toList();
        List<Assign> assigns = assignJpaRepository.saveAll(entities).stream()
            .map(AssignEntity::toModel)
            .toList();
        return new Assigns(assigns);
    }

    @Override
    public Assigns findAssignsByCardId(Long cardId) {
        List<AssignEntity> entities = findAssigns(cardId);
        List<Assign> assigns = entities.stream()
            .map(AssignEntity::toModel)
            .toList();
        return new Assigns(assigns);
    }

    @Override
    public Card update(Card updateCard) {
        return cardJpaRepository
            .saveAndFlush(CardEntity.fromModel(updateCard))
            .toModel();
    }

    @Override
    public Assigns reassignAll(List<Long> assignedUserIds, Long cardId) {
        List<AssignEntity> entities = findAssigns(cardId);
        if (!entities.isEmpty()) {
            assignJpaRepository.deleteAllByCardId(cardId);
        }
        return assignAll(assignedUserIds, cardId);
    }

    @Override
    public void deleteByTaskStateId(Long stateId) {
        List<CardEntity> found = cardJpaRepository.findAllByTaskStateId(stateId);
        for (CardEntity cardEntity : found) {
            assignJpaRepository.deleteAllByCardId(cardEntity.getId());
        }
        cardJpaRepository.deleteAll(found);
    }

    @Override
    public void deleteCard(Long cardId) {
        cardJpaRepository.deleteById(cardId);
        assignJpaRepository.deleteAllByCardId(cardId);
    }

    @Override
    public boolean isExist(Long cardId) {
        return cardJpaRepository.findById(cardId).isPresent();
    }

    @Override
    public List<Card> getAllCards(Pageable pageable, Long boardId) {
        return cardJpaRepository.getAllSortedCards(pageable, boardId)
            .stream()
            .map(CardEntity::toModel)
            .toList();
    }

    @Override
    public Card get(Long cardId) {
        CardEntity found = findById(cardId);
        return found.toModel();
    }

    private List<AssignEntity> findAssigns(Long cardId) {
        return assignJpaRepository.findAllByCardId(cardId);
    }

    private CardEntity findById(Long cardId) {
        return cardJpaRepository.findById(cardId).orElseThrow(
            () -> new CustomRuntimeException("해당 카드를 찾을 수 없습니다.")
        );
    }

}
