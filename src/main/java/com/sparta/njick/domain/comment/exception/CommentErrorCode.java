package com.sparta.njick.domain.comment.exception;

import lombok.Getter;

@Getter
public enum CommentErrorCode {
    COMMENT_ERROR_CODE_NOT_FOUND("해당 댓글을 찾을 수 없습니다"),
    COMMENT_ERROR_IS_NOT_WRITER("작성자가 아닙니다. 권한이 없습니다");
    private final String message;

    CommentErrorCode(String message) {
        this.message = message;
    }
}
