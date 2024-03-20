package com.sparta.njick.domain.card.service;

import com.sparta.njick.domain.assign.model.Assigns;
import com.sparta.njick.domain.card.dto.request.CardCreateRequestDto;
import com.sparta.njick.domain.card.dto.request.CardUpdateRequestDto;
import com.sparta.njick.domain.card.dto.response.CardResponseDto;
import com.sparta.njick.domain.card.model.Card;
import com.sparta.njick.domain.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    @Transactional
    public CardResponseDto createCard(CardCreateRequestDto requestDto, Long boardId, Long userId) {
        //TODO: 보드에 초대된 사용자인지 검증

        Card card = cardRepository.save(requestDto, boardId, userId);
        Assigns assigns = cardRepository.assignAll(
            requestDto.getAssignedUserIds(), card.getId());
        return new CardResponseDto(card, assigns.getAssigns());
    }

    @Override
    @Transactional
    public CardResponseDto getCard(Long userId, Long boardId, Long cardId) {
        //TODO: 보드에 초대된 사용자인지 검증

        Card found = cardRepository.get(cardId);
        found.validateBoardId(boardId);

        Assigns assigns = cardRepository.findAssignsByCardId(cardId);

        return new CardResponseDto(found, assigns.getAssigns());
    }

    @Override
    @Transactional
    public CardResponseDto updateCard(CardUpdateRequestDto requestDto, Long boardId, Long cardId,
        Long userId) {
        //TODO: 보드에 초대된 사용자인지 검증
        Card found = cardRepository.get(cardId);
        Card updateCard = found.update(requestDto, boardId);
        Card updated = cardRepository.update(updateCard);

        Assigns assigns = cardRepository.reassignAll(requestDto.getAssignedUserIds(), cardId);
        return new CardResponseDto(updated, assigns.getAssigns());
    }
}
