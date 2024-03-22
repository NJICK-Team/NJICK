package com.sparta.njick.domain.card.infrastructure.search;

import com.sparta.njick.domain.card.infrastructure.entity.CardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardSearch {

    Page<CardEntity> getAllSortedCards(Pageable pageable, Long boardId);
}
