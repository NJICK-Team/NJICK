package com.sparta.njick.domain.taskState.service;

import com.sparta.njick.domain.taskState.dto.requestDto.TaskStateRequestDto;

public interface TaskStateService {

    void createTaskState(TaskStateRequestDto requestDto/*, Long userId*/);
}
