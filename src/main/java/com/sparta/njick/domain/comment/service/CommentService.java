package com.sparta.njick.domain.comment.service;

import com.sparta.njick.domain.comment.dto.requestDto.CommentRequestDto;

public interface CommentService {

    void createComment(CommentRequestDto requestDto, Long boardId, Long cardId, Long userId);
}
