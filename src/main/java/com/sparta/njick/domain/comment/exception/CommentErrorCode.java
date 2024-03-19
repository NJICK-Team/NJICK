package com.sparta.njick.domain.comment.exception;

import lombok.Getter;

@Getter
public enum CommentErrorCode {
    COMMENT_ERROR_CODE_NOT_FOUND("해당 댓글을 찾을 수 없습니다");
    private final String message;

    CommentErrorCode(String message) {
        this.message = message;
    }
}
