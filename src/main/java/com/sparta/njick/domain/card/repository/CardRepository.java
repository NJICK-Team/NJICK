package com.sparta.njick.domain.card.repository;

import com.sparta.njick.domain.assign.model.Assigns;
import com.sparta.njick.domain.card.dto.request.CardCreateRequestDto;
import com.sparta.njick.domain.card.model.Card;
import java.util.List;

public interface CardRepository {

    Card save(CardCreateRequestDto requestDto, Long boardId, Long userId);

    Card get(Long cardId);

    Assigns assignAll(List<Long> assignedUserIds, Long cardId);
    
    Assigns findAssignsByCardId(Long cardId);

    Card update(Card updateCard);

    Assigns reassignAll(List<Long> assignedUserIds, Long cardId);

    void deleteByTaskStateId(Long stateId);

    void deleteCard(Long cardId);
}
