package com.sparta.njick.domain.comment.exception;

import org.springframework.http.HttpStatus;

public class CustomCommentException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    public CustomCommentException(final CommentErrorCode errorCode) {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
