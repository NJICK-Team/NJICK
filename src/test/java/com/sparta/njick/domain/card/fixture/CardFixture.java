package com.sparta.njick.domain.card.fixture;

import com.sparta.njick.domain.card.dto.request.CardCreateRequestDto;
import com.sparta.njick.domain.card.model.Card;
import java.time.LocalDateTime;
import java.util.List;

public interface CardFixture {

    Long TEST_CARD_ID = 1L;
    String TEST_TITLE = "title";
    String TEST_DESCRIPTION = "description";
    String TEST_CARD_COLOR = "0x0000";
    LocalDateTime TEST_DEAD_LINE = LocalDateTime.of(2024, 4, 15, 17, 24);
    Long TEST_BOARD_ID = 1L;
    Long TEST_TASK_STATE_ID = 1L;
    Long TEST_USER_ID = 1L;

    Card TEST_CARD = Card.builder()
        .title(TEST_TITLE)
        .description(TEST_DESCRIPTION)
        .cardColor(TEST_CARD_COLOR)
        .deadline(TEST_DEAD_LINE)
        .boardId(TEST_BOARD_ID)
        .taskStateId(TEST_TASK_STATE_ID)
        .creatorId(TEST_USER_ID)
        .build();

    CardCreateRequestDto TEST_CREATE_REQUEST_DTO = CardCreateRequestDto.builder()
        .title(TEST_TITLE)
        .description(TEST_DESCRIPTION)
        .cardColor(TEST_CARD_COLOR)
        .deadline(TEST_DEAD_LINE)
        .taskStateId(TEST_TASK_STATE_ID)
        .assignedUserIds(List.of(1L, 2L, 3L))
        .build();
}
