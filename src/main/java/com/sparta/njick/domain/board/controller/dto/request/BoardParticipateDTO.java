package com.sparta.njick.domain.board.controller.dto.request;

public record BoardParticipateDTO(
        Long boardId,
        Long participatorId
) {
}
