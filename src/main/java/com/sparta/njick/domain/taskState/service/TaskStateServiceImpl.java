package com.sparta.njick.domain.taskState.service;

import com.sparta.njick.domain.taskState.dto.requestDto.TaskStateRequestDto;
import com.sparta.njick.domain.taskState.dto.responseDto.TaskStateResponseDto;
import com.sparta.njick.domain.taskState.entity.TaskState;
import com.sparta.njick.domain.taskState.model.TaskStateModel;
import com.sparta.njick.domain.taskState.repository.TaskStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskStateServiceImpl implements TaskStateService {

    private final TaskStateRepository taskStateRepository;
    private final BoardRepository boardRepository;

    @Override
    public void createTaskState(TaskStateRequestDto requestDto, Long userId) {
        boardRepository.isParticipated(requestDto.getBoardId(), userId);

        taskStateRepository.save(new TaskState(requestDto)).toModel();
    }

    @Override
    public TaskStateResponseDto updateTaskState(TaskStateRequestDto requestDto, Long stateId,
        Long userId) {
        boardRepository.isParticipated(requestDto.getBoardId(), userId);

        TaskStateModel model = taskStateRepository.update(requestDto.getBoardId(),
            requestDto.getName(), stateId);

        return new TaskStateResponseDto(model);
    }

}
