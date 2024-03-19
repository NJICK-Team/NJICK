package com.sparta.njick.domain.card.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Card {

    private final String title;
    private final String description;
    private final CardColor cardColor;
    private final LocalDateTime deadline;
    private final Long boardId;
    private final Long taskStateId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
