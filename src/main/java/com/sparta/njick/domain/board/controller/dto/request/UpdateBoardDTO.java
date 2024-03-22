package com.sparta.njick.domain.board.controller.dto.request;

public record UpdateBoardDTO(
        String title,
        String description,
        String color
) {
}
