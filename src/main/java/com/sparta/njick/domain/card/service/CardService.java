package com.sparta.njick.domain.card.service;

import com.sparta.njick.domain.card.dto.request.CardCreateRequestDto;
import com.sparta.njick.domain.card.dto.response.CardResponseDto;
import com.sparta.njick.domain.card.model.Card;
import com.sparta.njick.domain.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardResponseDto createCard(CardCreateRequestDto requestDto, Long boardId, Long userId) {
        //보드에 초대된 사용자인지 검증

        Card card = Card.builder()
            .title(requestDto.getTitle())
            .description(requestDto.getDescription())
            .cardColor(requestDto.getCardColor())
            .deadline(requestDto.getDeadline())
            .taskStateId(requestDto.getTaskStateId())
            .boardId(boardId)
            .creatorId(userId)
            .build();
        card = cardRepository.save(card);
        return new CardResponseDto(card);
    }
}
