package com.sparta.njick.domain.comment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentModel {

    private Long commentId;
    private String content;
    private Long writerId;
    private Long cardId;
}
