package com.sparta.njick.domain.board.service.dto;

import com.sparta.njick.domain.board.model.Board;
import lombok.Builder;

@Builder
public record BoardInfoDTO(Long id,
                           String title,
                           String description,
                           String color,
                           Long creatorId
) {

    public static BoardInfoDTO from(Board board) {
        return BoardInfoDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .description(board.getDescription())
                .color(board.getColor())
                .creatorId(board.getCreatorId())
                .build();
    }
}
