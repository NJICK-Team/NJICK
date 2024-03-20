package com.sparta.njick.domain.card.service;

import com.sparta.njick.domain.assign.model.Assign;
import com.sparta.njick.domain.card.dto.request.CardCreateRequestDto;
import com.sparta.njick.domain.card.dto.request.CardUpdateRequestDto;
import com.sparta.njick.domain.card.dto.response.CardResponseDto;
import com.sparta.njick.domain.card.model.Card;
import com.sparta.njick.domain.card.repository.CardRepository;
import java.util.List;
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
        //보드에 초대된 사용자인지 검증

        Card card = cardRepository.save(requestDto, boardId, userId);
        List<Assign> assigns = cardRepository.assignAll(
            requestDto.getAssignedUserIds(), card.getId());
        return new CardResponseDto(card, assigns);
    }

    @Override
    @Transactional
    public CardResponseDto getCard(Long userId, Long boardId, Long cardId) {
        //보드에 초대된 사용자인지 검증

        Card found = cardRepository.get(cardId);
        found.validateBoardId(boardId);

        List<Assign> assigns = cardRepository.findAssignsByCardId(cardId);

        return new CardResponseDto(found, assigns);
    }

    @Override
    public CardResponseDto updateCard(CardUpdateRequestDto requestDto, Long boardId, Long cardId,
        Long userId) {
        //보드에 초대된 사용자인지 검증

        return null;
    }
}
