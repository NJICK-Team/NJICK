package com.sparta.njick.domain.comment.dto.responseDto;

import com.sparta.njick.domain.comment.model.CommentModel;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private Long commentId;
    private String content;
    private Long writerId;
    private Long cardId;

    public CommentResponseDto(CommentModel model) {
        this.commentId = model.getCommentId();
        this.content = model.getContent();
        this.writerId = model.getWriterId();
        this.cardId = model.getCardId();
    }
}
