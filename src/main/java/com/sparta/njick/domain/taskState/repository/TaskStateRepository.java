package com.sparta.njick.domain.taskState.repository;


import com.sparta.njick.domain.taskState.entity.TaskState;

public interface TaskStateRepository {

    TaskState save(TaskState taskState);
}
