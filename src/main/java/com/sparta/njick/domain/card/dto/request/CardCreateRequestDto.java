package com.sparta.njick.domain.card.dto.request;

import com.sparta.njick.domain.card.model.CardColor;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
public class CardCreateRequestDto {

    @NotBlank
    @Length(max = 100)
    private String title;
    @NotBlank
    @Length(max = 255)
    private String description;
    @NotBlank
    private CardColor cardColor;
    private LocalDateTime deadline;
    private Long taskStateId;
    private List<Long> assignedUserIds;
}
