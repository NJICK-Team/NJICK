package com.sparta.njick.domain.comment.controller;

import com.sparta.njick.domain.comment.dto.requestDto.CommentRequestDto;
import com.sparta.njick.domain.comment.dto.responseDto.CommentResponseDto;
import com.sparta.njick.domain.comment.service.CommentService;
import com.sparta.njick.domain.taskState.common.CommonResponseDto;
import com.sparta.njick.domain.user.userDetails.UserDetailsImpl;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/cards/{cardId}/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommonResponseDto<Void>> createComment(
        @Valid @RequestBody CommentRequestDto requestDto,
        @PathVariable Long cardId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.createComment(requestDto, cardId,
            userDetails.getUser().getId());
        return CommonResponseDto.of(HttpStatus.OK, "카드댓글 생성 성공", null);
    }

    @GetMapping
    public ResponseEntity<CommonResponseDto<List<CommentResponseDto>>> getComments(
        @PathVariable Long cardId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<CommentResponseDto> responseDto = commentService.getComments(cardId,
            userDetails.getUser().getId());
        return CommonResponseDto.of(HttpStatus.OK, "카드댓글 조회 성공", responseDto);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommonResponseDto<CommentResponseDto>> updateComment(
        @Valid @RequestBody CommentRequestDto requestDto,
        @PathVariable Long cardId,
        @PathVariable Long commentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        CommentResponseDto responseDto = commentService.updateComment(requestDto, cardId,
            commentId, userDetails.getUser().getId());
        return CommonResponseDto.of(HttpStatus.OK, "카드댓글 수정 성공", responseDto);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommonResponseDto<Void>> deleteComment(
        @PathVariable Long cardId,
        @PathVariable Long commentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.deleteComment(cardId, commentId, userDetails.getUser().getId());
        return CommonResponseDto.of(HttpStatus.OK, "카드댓글 삭제 성공", null);
    }
}
