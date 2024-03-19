package com.sparta.njick.domain.taskState.service;

import com.sparta.njick.domain.taskState.dto.requestDto.TaskStateRequestDto;
import com.sparta.njick.domain.taskState.dto.responseDto.TaskStateResponseDto;
import com.sparta.njick.domain.taskState.entity.TaskState;
import com.sparta.njick.domain.taskState.model.TaskStateModel;
import com.sparta.njick.domain.taskState.repository.TaskStateRepository;
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
    pirvate final CardRepository cardRepository;

    @Override
    public void createTaskState(TaskStateRequestDto requestDto, Long userId) {
        boardRepository.isParticipated(requestDto.getBoardId(), userId);

        taskStateRepository.save(new TaskState(requestDto));
    }

    @Override
    public TaskStateResponseDto updateTaskState(TaskStateRequestDto requestDto, Long stateId,
        Long userId) {
        boardRepository.isParticipated(requestDto.getBoardId(), userId);

        TaskStateModel model = taskStateRepository.update(requestDto.getBoardId(),
            requestDto.getName(), stateId);

        return new TaskStateResponseDto(model);
    }

    @Override
    public void deleteTaskState(Long stateId, Long userId) {
        TaskStateModel model = taskStateRepository.findByIdOrElseThrow(stateId);
        Long boardId = model.getBoardId();
        boardRepository.isParticipated(boardId, userId);

        taskStateRepository.delete(stateId);

        //TODO 해당 stateId를 가진 카드를 다 가져옴 => delete
        cardRepository.deleteByTaskStateId(stateId);
    }

    @Override
    public List<TaskStateResponseDto> getTaskStates(Long boardId, Long userId) {
        boardRepository.isParticipated(boardId, userId);

        List<TaskStateResponseDto> response = new ArrayList<>();
        List<TaskStateModel> models = taskStateRepository.findAllByBoardId(boardId);

        for(TaskStateModel model : models){
            response.add(new TaskStateResponseDto(model));
        }

        return response;
    }

}
