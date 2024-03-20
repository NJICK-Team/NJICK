package com.sparta.njick.domain.card.repository;

import com.sparta.njick.domain.assign.model.Assign;
import com.sparta.njick.domain.card.dto.CardInfoDto;
import com.sparta.njick.domain.card.model.Card;
import java.util.List;

public interface CardRepository {

    CardInfoDto save(Card model);

    Card get(Long cardId);

    void assignAll(List<Assign> assigns);

    List<Assign> findAssignsByCardId(Long cardId);

    void deleteByTaskStateId(Long stateId);
}
