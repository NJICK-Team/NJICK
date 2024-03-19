package com.sparta.njick.domain.taskState.controller;

import com.sparta.njick.domain.taskState.common.CommonResponseDto;
import com.sparta.njick.domain.taskState.dto.requestDto.TaskStateRequestDto;
import com.sparta.njick.domain.taskState.dto.responseDto.TaskStateResponseDto;
import com.sparta.njick.domain.taskState.service.TaskStateService;
import com.sparta.njick.domain.user.userDetails.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        taskStatesService.createTaskState(requestDto, userDetails.getUser().getUserId());
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

}
