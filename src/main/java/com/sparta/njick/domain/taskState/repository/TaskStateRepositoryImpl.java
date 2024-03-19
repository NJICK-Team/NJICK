package com.sparta.njick.domain.taskState.repository;

import com.sparta.njick.domain.taskState.entity.TaskState;
import com.sparta.njick.domain.taskState.exception.CustomTaskStateException;
import com.sparta.njick.domain.taskState.exception.TaskStateErrorCode;
import com.sparta.njick.domain.taskState.model.TaskStateModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TaskStateRepositoryImpl implements TaskStateRepository {

    private final TaskStateJpaRepository taskStateJpaRepository;

    @Override
    public TaskState save(TaskState taskState) {
        return taskStateJpaRepository.save(taskState);
    }

    @Override
    public TaskStateModel update(Long boardId, String name, Long stateId) {
        TaskState taskState = taskStateJpaRepository.findById(stateId).orElseThrow(
            () -> new CustomTaskStateException(TaskStateErrorCode.TASKSTATE_ERROR_CODE_NOT_FOUND)
        );
        if (!taskState.getBoardId().equals(boardId)) {
            throw new CustomTaskStateException(
                TaskStateErrorCode.TASKSTATE_ERROR_CODE_BOARDID_MISMATCH);
        }
        taskState.update(name);
        return taskState.toModel();
    }
}
