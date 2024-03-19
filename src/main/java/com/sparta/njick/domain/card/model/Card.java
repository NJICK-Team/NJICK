package com.sparta.njick.domain.card.model;

import com.sparta.njick.global.exception.CustomRuntimeException;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Card {

    private final String title;
    private final String description;
    private final String cardColor;
    private final LocalDateTime deadline;
    private final Long boardId;
    private final Long taskStateId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final Long creatorId;

    public void validateBoardId(Long boardId) {
        if (!Objects.equals(this.boardId, boardId)) {
            throw new CustomRuntimeException("해당 보드에 맞는 카드가 아닙니다.");
        }
    }
}
