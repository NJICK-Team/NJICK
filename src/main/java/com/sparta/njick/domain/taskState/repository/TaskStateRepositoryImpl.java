package com.sparta.njick.domain.taskState.repository;

import com.sparta.njick.domain.taskState.entity.TaskState;
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
}
