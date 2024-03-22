package com.sparta.njick.domain.comment.service;

import com.sparta.njick.domain.comment.dto.requestDto.CommentRequestDto;
import com.sparta.njick.domain.comment.dto.responseDto.CommentResponseDto;
import java.util.List;

public interface CommentService {

    void createComment(CommentRequestDto requestDto, Long cardId, Long userId);

    List<CommentResponseDto> getComments(Long cardId, Long userId);

    CommentResponseDto updateComment(CommentRequestDto requestDto, Long cardId,
        Long commentId, Long userId);

    void deleteComment(Long cardId, Long commentId, Long userId);
}
