package com.sparta.njick.domain.card.repository;

import com.sparta.njick.domain.assign.model.Assign;
import com.sparta.njick.domain.card.dto.request.CardCreateRequestDto;
import com.sparta.njick.domain.card.model.Card;
import java.util.List;

public interface CardRepository {

    Card save(CardCreateRequestDto requestDto, Long boardId, Long userId);

    Card get(Long cardId);

    List<Assign> assignAll(List<Long> assignedUserIds, Long cardId);

    List<Assign> findAssignsByCardId(Long cardId);

    void deleteByTaskStateId(Long stateId);
}
