package com.sparta.njick.domain.taskState.exception;

import org.springframework.http.HttpStatus;

public class CustomTaskStateException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    public CustomTaskStateException(final TaskStateErrorCode errorCode) {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
