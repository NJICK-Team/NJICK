package com.sparta.njick.domain.card.service;

import com.sparta.njick.domain.card.dto.request.CardCreateRequestDto;
import com.sparta.njick.domain.card.dto.request.CardUpdateRequestDto;
import com.sparta.njick.domain.card.dto.response.CardResponseDto;

public interface CardService {

    CardResponseDto createCard(CardCreateRequestDto requestDto, Long boardId, Long userId);

    CardResponseDto getCard(Long userId, Long boardId, Long cardId);

    CardResponseDto updateCard(CardUpdateRequestDto requestDto, Long boardId, Long cardId, Long userId);

    void deleteCard(Long boardId, Long cardId, Long userId);
}
