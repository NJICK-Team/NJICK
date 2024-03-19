package com.sparta.njick.domain.board.controller.dto.request;

public record UpdateBoardDTO(
        Long boardId,
        Long requestUserId,
        String title,
        String description,
        String color
) {
}
