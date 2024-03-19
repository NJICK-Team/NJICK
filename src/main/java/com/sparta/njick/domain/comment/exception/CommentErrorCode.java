package com.sparta.njick.domain.comment.exception;

import lombok.Getter;

@Getter
public enum CommentErrorCode {
    COMMENT_ERROR_CODE("");
    private final String message;

    CommentErrorCode(String message) {
        this.message = message;
    }
}
