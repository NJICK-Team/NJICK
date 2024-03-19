package com.sparta.njick.domain.taskState.controller;

import com.sparta.njick.domain.taskState.common.CommonResponseDto;
import com.sparta.njick.domain.taskState.dto.requestDto.TaskStateRequestDto;
import com.sparta.njick.domain.taskState.service.TaskStateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/taskstates")
@RequiredArgsConstructor
public class TaskStateController {

    private final TaskStateService taskStatesService;

    @PostMapping
    public ResponseEntity<CommonResponseDto<Void>> createTaskState(
        @Valid @RequestBody TaskStateRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        taskStatesService.createTaskState(requestDto/*, userDetails.getUser().getUserId()*/);
        return CommonResponseDto.of(HttpStatus.OK, "작업상태 작성 성공", null);
    }

    @PutMapping("/{stateId}")
    public ResponseEntity<CommonResponseDto<TaskStateResponseDto>> updateTaskStates(
        @Valid @RequestBody TaskStateRequestDto requestDto,
        @PathVariable Long stateId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        TaskStateResponseDto responseDto = taskStatesService.updateTaskState(requestDto, stateId,
            userDetails.getUser().getUserId());
        return CommonResponseDto.of(HttpStatus.OK, "작업상태 수정 성공", responseDto);
    }

    @DeleteMapping("/{stateId}")
    public ResponseEntity<CommonResponseDto<Void>> deleteTaskState(
        @PathVariable Long stateId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        taskStatesService.deleteTaskState(stateId, userDetails.getUser().getId());
        return CommonResponseDto.of(HttpStatus.OK, "작업상태 삭제 성공", null);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<CommonResponseDto<List<TaskStateResponseDto>>> getTaskStates(
        @PathVariable Long boardId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<TaskStateResponseDto> responseDto = taskStatesService.getTaskStates(boardId,
            userDetails.getUser().getId());
        return CommonResponseDto.of(HttpStatus.OK, "보드의 작업상태 조회 성공", responseDto);
    }
}
