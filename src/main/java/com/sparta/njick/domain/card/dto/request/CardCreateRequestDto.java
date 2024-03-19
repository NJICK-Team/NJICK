package com.sparta.njick.domain.card.dto.request;

import com.sparta.njick.domain.card.model.CardColor;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CardCreateRequestDto {

    private String title;
    private String description;
    private CardColor cardColor;
    private LocalDateTime deadline;
    private Long taskStateId;
}
