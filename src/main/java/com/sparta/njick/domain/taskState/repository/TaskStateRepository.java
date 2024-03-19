package com.sparta.njick.domain.taskState.repository;


import com.sparta.njick.domain.taskState.entity.TaskState;
import com.sparta.njick.domain.taskState.model.TaskStateModel;

public interface TaskStateRepository {

    TaskState save(TaskState taskState);

    TaskStateModel update(Long boardId, String name, Long stateId);
}
