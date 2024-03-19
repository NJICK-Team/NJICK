package com.sparta.njick.domain.card.controller;

import com.sparta.njick.domain.card.dto.request.CardCreateRequestDto;
import com.sparta.njick.domain.card.dto.response.CardResponseDto;
import com.sparta.njick.domain.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/boards/{boardId}/cards")
@RestController
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<CardResponseDto> createCard(
        @PathVariable Long boardId,
        @RequestBody CardCreateRequestDto requestDto) {
        CardResponseDto responseDto = cardService.createCard(requestDto, boardId, 1L);
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("{cardId}")
    public ResponseEntity<CardResponseDto> getCard(
        @PathVariable Long boardId,
        @PathVariable Long cardId) {
        CardResponseDto responseDto = cardService.getCard(1L, boardId, cardId);
        return ResponseEntity.ok().body(responseDto);
    }
}
