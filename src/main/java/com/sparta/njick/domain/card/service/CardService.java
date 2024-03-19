package com.sparta.njick.domain.card.service;

import com.sparta.njick.domain.card.dto.request.CardCreateRequestDto;
import com.sparta.njick.domain.card.dto.response.CardResponseDto;
import org.springframework.stereotype.Service;

public interface CardService {

    CardResponseDto createCard(CardCreateRequestDto requestDto, Long boardId, Long userId);
    CardResponseDto getCard(Long userId, Long boardId, Long cardId);
}
