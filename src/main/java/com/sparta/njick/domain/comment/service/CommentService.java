package com.sparta.njick.domain.comment.service;

import com.sparta.njick.domain.comment.dto.requestDto.CommentRequestDto;
import com.sparta.njick.domain.comment.dto.responseDto.CommentResponseDto;
import java.util.List;

public interface CommentService {

    void createComment(CommentRequestDto requestDto, Long boardId, Long cardId, Long userId);

    List<CommentResponseDto> getComments(Long boardId, Long cardId, Long userId);
}
