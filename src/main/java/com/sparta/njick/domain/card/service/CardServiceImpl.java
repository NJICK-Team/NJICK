package com.sparta.njick.domain.card.service;

import com.sparta.njick.domain.assign.model.Assign;
import com.sparta.njick.domain.card.dto.CardInfoDto;
import com.sparta.njick.domain.card.dto.request.CardCreateRequestDto;
import com.sparta.njick.domain.card.dto.response.CardResponseDto;
import com.sparta.njick.domain.card.model.Card;
import com.sparta.njick.domain.card.repository.CardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
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

        CardInfoDto infoDto = cardRepository.save(card);

        List<Long> assignedUserIds = requestDto.getAssignedUserIds();
        List<Assign> assigns = assignedUserIds.stream()
            .map(id -> Assign.builder()
                .userId(id)
                .cardId(infoDto.getId())
                .build())
            .toList();
        cardRepository.assignAll(assigns);

        return new CardResponseDto(card, assigns);
    }

    @Override
    public CardResponseDto getCard(Long userId, Long boardId, Long cardId) {
        //보드에 초대된 사용자인지 검증

        Card found = cardRepository.get(boardId, cardId);
        return null;
    }
}
