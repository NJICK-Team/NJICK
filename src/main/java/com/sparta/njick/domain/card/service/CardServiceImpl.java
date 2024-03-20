package com.sparta.njick.domain.card.service;

import com.sparta.njick.domain.assign.model.Assigns;
import com.sparta.njick.domain.board.repository.BoardRepository;
import com.sparta.njick.domain.card.dto.request.CardCreateRequestDto;
import com.sparta.njick.domain.card.dto.request.CardUpdateRequestDto;
import com.sparta.njick.domain.card.dto.response.CardResponseDto;
import com.sparta.njick.domain.card.model.Card;
import com.sparta.njick.domain.card.repository.CardRepository;
import com.sparta.njick.global.exception.CustomRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final BoardRepository boardRepository;

    @Override
    public CardResponseDto createCard(CardCreateRequestDto requestDto, Long boardId, Long userId) {
        if (!boardRepository.isParticipated(boardId, userId)) {
            throw new CustomRuntimeException("해당 보드에 참여중인 유저가 아닙니다.");
        }

        Card card = cardRepository.save(requestDto, boardId, userId);
        Assigns assigns = cardRepository.assignAll(
            requestDto.getAssignedUserIds(), card.getId());
        return new CardResponseDto(card, assigns.getAssigns());
    }

    @Override
    @Transactional(readOnly = true)
    public CardResponseDto getCard(Long userId, Long boardId, Long cardId) {
        if (!boardRepository.isParticipated(boardId, userId)) {
            throw new CustomRuntimeException("해당 보드에 참여중인 유저가 아닙니다.");
        }

        Card found = cardRepository.get(cardId);
        found.validateBoardId(boardId);

        Assigns assigns = cardRepository.findAssignsByCardId(cardId);

        return new CardResponseDto(found, assigns.getAssigns());
    }

    @Override
    public CardResponseDto updateCard(CardUpdateRequestDto requestDto, Long boardId, Long cardId,
        Long userId) {
        if (!boardRepository.isParticipated(boardId, userId)) {
            throw new CustomRuntimeException("해당 보드에 참여중인 유저가 아닙니다.");
        }

        Card found = cardRepository.get(cardId);
        Card updateCard = found.update(requestDto, boardId);
        Card updated = cardRepository.update(updateCard);

        Assigns assigns = cardRepository.reassignAll(requestDto.getAssignedUserIds(), cardId);
        return new CardResponseDto(updated, assigns.getAssigns());
    }

    @Override
    public void deleteCard(Long boardId, Long cardId, Long userId) {
        if (!boardRepository.isParticipated(boardId, userId)) {
            throw new CustomRuntimeException("해당 보드에 참여중인 유저가 아닙니다.");
        }
        Card card = cardRepository.get(cardId);
        card.validateBoardId(boardId);

        cardRepository.deleteCard(cardId);
    }
}
