package com.sparta.njick.domain.card.repository;

import com.sparta.njick.domain.card.dto.CardInfoDto;
import com.sparta.njick.domain.card.model.Card;

public interface CardRepository {

    CardInfoDto save(Card model);
    Card get(Long boardId, Long cardId);
}
