package com.sparta.njick.domain.comment.controller;

import com.sparta.njick.domain.comment.dto.requestDto.CommentRequestDto;
import com.sparta.njick.domain.comment.service.CommentService;
import com.sparta.njick.domain.taskState.common.CommonResponseDto;
import com.sparta.njick.domain.user.userDetails.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/boards/{boardId}/cards/{cardId}/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommonResponseDto<Void>> createComment(
        @Valid @RequestBody CommentRequestDto requestDto,
        @PathVariable Long boardId,
        @PathVariable Long cardId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.createComment(requestDto, boardId, cardId,
            userDetails.getUser().getUserId());
    }

}
