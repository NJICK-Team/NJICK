package com.sparta.njick.domain.taskState.service;

import com.sparta.njick.domain.board.repository.BoardRepository;
import com.sparta.njick.domain.card.repository.CardRepository;
import com.sparta.njick.domain.taskState.dto.requestDto.TaskStateRequestDto;
import com.sparta.njick.domain.taskState.dto.responseDto.TaskStateResponseDto;
import com.sparta.njick.domain.taskState.entity.TaskState;
import com.sparta.njick.domain.taskState.model.TaskStateModel;
import com.sparta.njick.domain.taskState.repository.TaskStateRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskStateServiceImpl implements TaskStateService {

    private final TaskStateRepository taskStateRepository;
    private final BoardRepository boardRepository;
    private final CardRepository cardRepository;

    @Override
    public void createTaskState(TaskStateRequestDto requestDto, Long userId) {
        isParticipated(requestDto.getBoardId(), userId);

        taskStateRepository.save(new TaskState(requestDto));
    }

    @Override
    public TaskStateResponseDto updateTaskState(TaskStateRequestDto requestDto, Long stateId,
        Long userId) {
        isParticipated(requestDto.getBoardId(), userId);

        TaskStateModel model = taskStateRepository.update(requestDto.getBoardId(),
            requestDto.getName(), stateId);

        return new TaskStateResponseDto(model);
    }

    @Override
    public void deleteTaskState(Long stateId, Long userId) {
        TaskStateModel model = taskStateRepository.findByIdOrElseThrow(stateId);
        isParticipated(model.getBoardId(), userId);

        taskStateRepository.delete(stateId);
        cardRepository.deleteByTaskStateId(stateId);
    }

    @Override
    public List<TaskStateResponseDto> getTaskStates(Long boardId, Long userId) {
        isParticipated(boardId, userId);

        List<TaskStateResponseDto> response = new ArrayList<>();
        List<TaskStateModel> models = taskStateRepository.findAllByBoardId(boardId);

        for (TaskStateModel model : models) {
            response.add(new TaskStateResponseDto(model));
        }

        return response;
    }

    private void isParticipated(Long boardId, Long userId) {
        if (!boardRepository.isParticipated(boardId, userId)) {
            throw new EntityNotFoundException("보드에 초대된 사용자가 아닙니다.");
        }
    }
}
