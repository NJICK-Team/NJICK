package com.sparta.njick.global.exception;

import com.sparta.njick.domain.comment.exception.CustomCommentException;
import com.sparta.njick.domain.taskState.common.CommonResponseDto;
import com.sparta.njick.domain.taskState.exception.CustomTaskStateException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalCustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleCustomRuntimeException(CustomRuntimeException e) {
        log.error(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(
                Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()));
    }

    @ExceptionHandler({
        IllegalArgumentException.class,
        NoSuchElementException.class,
        EntityNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> BadRequestExceptionHandler(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler({
        EntityExistsException.class,
        DuplicateKeyException.class
    })
    public ResponseEntity<ErrorResponse> ConflictExceptionHandler(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(CustomTaskStateException.class)
    public ResponseEntity<CommonResponseDto<Void>> customTaskStateExceptionHandler(
        CustomTaskStateException e) {
        return CommonResponseDto.of(HttpStatus.BAD_REQUEST, e.getMessage(), null);
    }

    @ExceptionHandler(CustomCommentException.class)
    public ResponseEntity<CommonResponseDto<Void>> customCommentExceptionHandler(
        CustomCommentException e) {
        return CommonResponseDto.of(HttpStatus.BAD_REQUEST, e.getMessage(), null);
    }
}
