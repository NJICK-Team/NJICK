package com.sparta.njick.domain.card.dto;

import com.sparta.njick.domain.card.infrastructure.entity.CardEntity;
import com.sparta.njick.domain.card.model.CardColor;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CardInfoDto {

    private Long id;
    private String title;
    private String description;
    private CardColor cardColor;
    private LocalDateTime deadline;
    private Long boardId;
    private Long taskStateId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long creatorId;

    public CardInfoDto(CardEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.cardColor = entity.getCardColor();
        this.deadline = entity.getDeadline();
        this.boardId = entity.getBoardId();
        this.taskStateId = entity.getTaskStateId();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
        this.creatorId = entity.getCreatorId();
    }
}
