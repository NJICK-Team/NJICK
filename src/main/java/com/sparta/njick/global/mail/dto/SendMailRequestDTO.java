package com.sparta.njick.global.mail.dto;

public record SendMailRequestDTO(
        String email,
        Long workspaceId
) {
}
