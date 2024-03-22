package com.sparta.njick.domain.board.service.dto;

import com.sparta.njick.domain.board.model.Board;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BoardInfoDTO(Long id,
                           String title,
                           String description,
                           String color,
                           Long creatorId,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt

) {

    public static BoardInfoDTO from(Board board) {
        return BoardInfoDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .description(board.getDescription())
                .color(board.getColor())
                .creatorId(board.getCreatorId())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }
}
