package com.sparta.njick.global.exception.errorcode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BoardErrorCode {
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "[ERROR] 잘못된 요청입니다"),
    UN_AUTHORIZED(HttpStatus.UNAUTHORIZED, "[ERROR] 권한이 없습니다"),
    DUPLICATE_PARTICIPATION(HttpStatus.BAD_REQUEST, "[ERROR] 참여중인 보드입니다");

    private final HttpStatus httpStatus;
    private final String message;

    BoardErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
