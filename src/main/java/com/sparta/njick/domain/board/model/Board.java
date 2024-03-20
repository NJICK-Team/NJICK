package com.sparta.njick.domain.board.model;

import com.sparta.njick.global.exception.CustomRuntimeException;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private Long id;
    private String title;
    private String description;
    private String color;
    private Long creatorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public boolean isNotOwner(Long requestUserId) {
        return !this.creatorId.equals(requestUserId);
    }

    public Board update(String title, String description, String color, Long requestUserId) {
        if (this.isNotOwner(requestUserId)) {
            throw new CustomRuntimeException("[ERROR] 권한이 없습니다");
        }

        return Board.builder()
                .id(this.id)
                .title(title)
                .description(description)
                .color(color)
                .creatorId(this.creatorId)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .deletedAt(this.deletedAt)
                .build();
    }
}
