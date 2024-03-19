package com.sparta.njick.domain.taskState.repository;


import com.sparta.njick.domain.taskState.entity.TaskState;
import com.sparta.njick.domain.taskState.model.TaskStateModel;
import java.util.List;

public interface TaskStateRepository {

    void save(TaskState taskState);

    TaskStateModel update(Long boardId, String name, Long stateId);

    void delete(Long stateId);

    TaskStateModel findByIdOrElseThrow(Long stateId);

    List<TaskStateModel> findAllByBoardId(Long boardId);
}
