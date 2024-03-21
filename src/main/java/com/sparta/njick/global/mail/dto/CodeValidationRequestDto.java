package com.sparta.njick.global.mail.dto;

public record CodeValidationRequestDto(
        String code,
        String email
) {
}
