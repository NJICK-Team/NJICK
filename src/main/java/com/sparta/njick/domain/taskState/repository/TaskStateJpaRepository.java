package com.sparta.njick.domain.taskState.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.njick.domain.taskState.entity.TaskState;

public interface TaskStateJpaRepository extends JpaRepository<TaskState, Long> {
}
