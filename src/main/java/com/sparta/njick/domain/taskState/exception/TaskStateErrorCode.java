package com.sparta.njick.domain.taskState.exception;

import lombok.Getter;

@Getter
public enum TaskStateErrorCode {

    TASKSTATE_ERROR_CODE_NOT_FOUND("해당 작업상태를 찾을 수 없습니다."),
    TASKSTATE_ERROR_CODE_BOARDID_MISMATCH("해당 작업상태는 이 보드에 존재하지 않습니다.");
    private final String message;

    TaskStateErrorCode(String message) {
        this.message = message;
    }
}
