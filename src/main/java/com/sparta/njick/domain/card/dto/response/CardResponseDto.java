package com.sparta.njick.domain.card.dto.response;

import com.sparta.njick.domain.card.model.Card;
import com.sparta.njick.domain.card.model.CardColor;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CardResponseDto {

    private String title;
    private String description;
    private CardColor cardColor;
    private LocalDateTime deadline;
    private Long boardId;
    private Long taskStateId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CardResponseDto(Card model) {
        this.title = model.getTitle();
        this.description = model.getDescription();
        this.cardColor = model.getCardColor();
        this.deadline = model.getDeadline();
        this.boardId = model.getBoardId();
        this.taskStateId = model.getTaskStateId();
        this.createdAt = model.getCreatedAt();
        this.updatedAt = model.getUpdatedAt();
    }
}
