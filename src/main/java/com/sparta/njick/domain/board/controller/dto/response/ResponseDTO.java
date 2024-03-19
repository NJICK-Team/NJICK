package com.sparta.njick.domain.board.controller.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO <T> {
    private HttpStatus code;
    private T data;

    public static ResponseDTO<String> success(HttpStatus status, String message) {
        return new ResponseDTO<>(status, message);
    }

    public static <T> ResponseDTO<T> error(HttpStatus status, T body) {
        return new ResponseDTO<>(status, body);
    }

    public static <T> ResponseDTO<T> success(T body) {
        return new ResponseDTO<>(HttpStatus.OK, body);
    }
}
