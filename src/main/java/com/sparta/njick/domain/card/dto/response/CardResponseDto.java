package com.sparta.njick.domain.card.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sparta.njick.domain.assign.model.Assign;
import com.sparta.njick.domain.card.model.Card;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class CardResponseDto {

    private String title;
    private String description;
    private String cardColor;
    private LocalDateTime deadline;
    private Long boardId;
    private Long taskStateId;
    private Long creatorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Long> assignedUserIds = new ArrayList<>();

    public CardResponseDto(Card model, List<Assign> assigns) {
        this.title = model.getTitle();
        this.description = model.getDescription();
        this.cardColor = model.getCardColor();
        this.deadline = model.getDeadline();
        this.boardId = model.getBoardId();
        this.taskStateId = model.getTaskStateId();
        this.creatorId = model.getCreatorId();
        this.createdAt = model.getCreatedAt();
        this.updatedAt = model.getUpdatedAt();
        for (Assign assign : assigns) {
            this.assignedUserIds.add(assign.getUserId());
        }
    }
}
