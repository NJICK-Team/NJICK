package com.sparta.njick.domain.card.mock;

import com.sparta.njick.domain.assign.model.Assign;
import com.sparta.njick.domain.assign.model.Assigns;
import com.sparta.njick.domain.card.dto.request.CardCreateRequestDto;
import com.sparta.njick.domain.card.dto.request.CardUpdateRequestDto;
import com.sparta.njick.domain.card.model.Card;
import com.sparta.njick.domain.card.repository.CardRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.Pageable;

public class FakeCardRepository implements CardRepository {

    private final Card card;
    private final Assign assign;

    public FakeCardRepository(Card card, Assign assign) {
        this.card = card;
        this.assign = assign;
    }

    @Override
    public Card save(CardCreateRequestDto requestDto, Long boardId, Long userId) {
        return Card.builder()
            .title(requestDto.getTitle())
            .description(requestDto.getDescription())
            .cardColor(requestDto.getCardColor())
            .taskStateId(requestDto.getTaskStateId())
            .deadline(requestDto.getDeadline())
            .boardId(boardId)
            .creatorId(userId)
            .build();
    }

    @Override
    public Card get(Long cardId) {
        return card;
    }

    @Override
    public Assigns assignAll(List<Long> assignedUserIds, Long cardId) {
        return new Assigns(List.of(assign));
    }

    @Override
    public Assigns findAssignsByCardId(Long cardId) {
        return new Assigns(List.of(assign));
    }

    @Override
    public Card update(Card updateCard) {
        return card.update(CardUpdateRequestDto.builder()
                .title(updateCard.getTitle())
                .description(updateCard.getDescription())
                .cardColor(updateCard.getCardColor())
                .deadline(updateCard.getDeadline())
                .taskStateId(updateCard.getTaskStateId())
            .build(), updateCard.getBoardId());
    }

    @Override
    public Assigns reassignAll(List<Long> assignedUserIds, Long cardId) {
        return new Assigns(List.of(assign));
    }

    @Override
    public void deleteByTaskStateId(Long stateId) {

    }

    @Override
    public void deleteCard(Long cardId) {

    }

    @Override
    public boolean isExist(Long cardId) {
        return Objects.equals(card.getId(), cardId);
    }

    @Override
    public List<Card> getAllCards(Pageable pageable, Long boardId) {
        return null;
    }
}
