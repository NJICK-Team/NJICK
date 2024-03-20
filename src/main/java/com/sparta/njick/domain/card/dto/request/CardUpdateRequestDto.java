package com.sparta.njick.domain.card.dto.request;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CardUpdateRequestDto {

    private String title;
    private String description;
    private String cardColor;
    private LocalDateTime deadline;
    private Long taskStateId;
    private List<Long> assignedUserIds;
}
