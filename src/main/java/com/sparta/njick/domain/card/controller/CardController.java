package com.sparta.njick.domain.card.controller;

import com.sparta.njick.domain.card.dto.request.CardCreateRequestDto;
import com.sparta.njick.domain.card.dto.request.CardUpdateRequestDto;
import com.sparta.njick.domain.card.dto.response.CardResponseDto;
import com.sparta.njick.domain.card.service.CardService;
import com.sparta.njick.domain.user.userDetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
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
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long boardId,
        @RequestBody CardCreateRequestDto requestDto) {
        CardResponseDto responseDto = cardService.createCard(requestDto, boardId,
            userDetails.getUser().getId());
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("{cardId}")
    public ResponseEntity<CardResponseDto> getCard(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long boardId,
        @PathVariable Long cardId) {
        CardResponseDto responseDto = cardService.getCard(userDetails.getUser().getId(),
            boardId, cardId);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("{cardId}")
    public ResponseEntity<CardResponseDto> updateCard(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long boardId,
        @PathVariable Long cardId,
        @RequestBody CardUpdateRequestDto requestDto) {
        CardResponseDto responseDto = cardService.updateCard(requestDto, boardId, cardId,
            userDetails.getUser().getId());
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("{cardId}")
    public ResponseEntity<Void> deleteCard(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long boardId,
        @PathVariable Long cardId) {
        cardService.deleteCard(boardId, cardId, userDetails.getUser().getId());
        return ResponseEntity.ok().build();
    }
}
