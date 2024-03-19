package com.sparta.njick.domain.taskState.service;

import com.sparta.njick.domain.taskState.dto.requestDto.TaskStateRequestDto;
import com.sparta.njick.domain.taskState.dto.responseDto.TaskStateResponseDto;
import java.util.List;

public interface TaskStateService {

    void createTaskState(TaskStateRequestDto requestDto, Long userId);

    TaskStateResponseDto updateTaskState(TaskStateRequestDto requestDto, Long stateId, Long userId);

    void deleteTaskState(Long stateId, Long userId);

    List<TaskStateResponseDto> getTaskStates(Long boardId, Long userId);
}
